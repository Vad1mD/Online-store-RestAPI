package com.onlineshop.onlineshop.services;

import com.onlineshop.onlineshop.Utils.Utils;
import com.onlineshop.onlineshop.dataModels.Item;
import com.onlineshop.onlineshop.persistance.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemManagementService {

    private ItemRepository itemRepository;

    private Utils utils;

    private HttpStatus status;

    @Autowired
    public ItemManagementService(ItemRepository itemRepository, Utils utils){
        this.itemRepository=itemRepository;
        this.utils=utils;
    }

    public ResponseEntity<List<Item>> getAllItemsInTheCatalog() {
        return new ResponseEntity<>(itemRepository.findAll(), HttpStatus.OK);
    }


    public ResponseEntity<Item> getItem(Long id, Long user){
        if(!utils.validateUser(user)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Optional<Item> item = itemRepository.findById(id);
        status = item.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        Item res = item.isPresent() ? item.get() : null;

        return new ResponseEntity<>(res, status);
    }


    public ResponseEntity<Item> addItem(Item item, Long userId){
        if(!utils.validateOwner(userId))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        if(item.getId() != 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemRepository.save(item),HttpStatus.OK);
    }


    public HttpStatus deleteItem(Long id, Long userId){
        if(!utils.validateOwner(userId))
            return HttpStatus.FORBIDDEN;
        boolean exists = itemRepository.existsById(id);
        if(!exists){
            return HttpStatus.NOT_FOUND;
        }
        itemRepository.deleteById(id);
        return HttpStatus.OK;
    }

    public ResponseEntity<Item> patchItem(Long userId, Item itemToPatch){

        if(!utils.validateOwner(userId))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        if(!itemRepository.existsById(itemToPatch.getId())){
            return new ResponseEntity<>(itemRepository.save(itemToPatch), HttpStatus.OK);
        }

        Optional<Item> optionalItem = itemRepository.findById(itemToPatch.getId());
        Item item = optionalItem.get();

        item.setAmount(itemToPatch.getAmount());;
        item.setName(itemToPatch.getName());
        item.setPrice(itemToPatch.getPrice());
        item.setCatalog_id(itemToPatch.getCatalog_id());

        return new ResponseEntity<>(itemRepository.save(itemToPatch), HttpStatus.OK);
    }


    public ResponseEntity<List<Item>> purchaseItem(List<Item>  itemsToPurchase, Long customerId){
        List<Item> purchasedItems = null;
        List<Long> ids = itemsToPurchase.stream().map(e -> e.getId()).collect(Collectors.toList());

        if(utils.validateCustomer(customerId)){
            List<Item> item = itemRepository.findByIdIn(ids);
            if(!item.isEmpty()){
                purchasedItems = validatePurchase(itemsToPurchase, item);
                item = itemRepository.saveAll(item);
                status= HttpStatus.OK;
            }else{
                status = HttpStatus.NOT_FOUND;
            }
        }else{
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<>(purchasedItems, status);
    }


    private List<Item> validatePurchase(List<Item> itemsToPurchase,List<Item>  items){

        Map<Long,Item> purchasedIdsAndItemMap = new HashMap<>();
        Map<Long,Item> existingIdsAndItemsMap = new HashMap<>();

        for(Item elem : itemsToPurchase)
            purchasedIdsAndItemMap.put(elem.getId(), elem);

        for(Item elem : items)
            existingIdsAndItemsMap.put(elem.getId(), elem);

        if(!validateAmount(purchasedIdsAndItemMap,existingIdsAndItemsMap)){
            return null;
        }

        performPurchase(purchasedIdsAndItemMap,existingIdsAndItemsMap);

        return itemsToPurchase;
    }

    private boolean validateAmount(Map<Long,Item> purchasedIdsAndItemMap, Map<Long,Item> existingIdsAndItemsMap){
        if(purchasedIdsAndItemMap.size() != existingIdsAndItemsMap.size())
            return false;

        return purchasedIdsAndItemMap.size() == purchasedIdsAndItemMap
                .entrySet()
                .stream()
                .filter(e -> e.getValue().getAmount() < existingIdsAndItemsMap.get(e.getKey()).getAmount())
                .count();
    }

    private void performPurchase(Map<Long,Item> purchasedIdsAndItemMap, Map<Long,Item> existingIdsAndItemsMap){
        for(Map.Entry<Long,Item> entry : existingIdsAndItemsMap.entrySet()){
            entry.getValue().setAmount(entry.getValue().getAmount() - purchasedIdsAndItemMap.get(entry.getKey()).getAmount());
        }

        for(Map.Entry<Long,Item> entry : purchasedIdsAndItemMap.entrySet()){
            entry.getValue().setName(existingIdsAndItemsMap.get(entry.getKey()).getName());
            entry.getValue().setPrice(existingIdsAndItemsMap.get(entry.getKey()).getPrice());
            entry.getValue().setCatalog_id(existingIdsAndItemsMap.get(entry.getKey()).getCatalog_id());
        }
    }

}
