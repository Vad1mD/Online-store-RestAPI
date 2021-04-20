package com.onlineshop.onlineshop.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ItemManagementService {

    private ItemRepository repository;

    @Autowired
    public ItemManagementService(ItemRepository repository){this.repository=repository;}

    public List<Item> getAllItemsInTheCatalog() {
        return repository.findAll();
    }

    public Item getItem(Long id){
        Optional<Item> item = repository.findById(id);
        return item.orElse(null);
    }

    public Item addItem(Item item){
        Item newItem = new Item(item.getName(), item.getPrice(),item.getAmount(), item.getCatalogName());
        return repository.save(newItem);
    }

    public void deleteItem(Long id){
        repository.deleteById(id);
    }

    public Item updateItem(Item itemToPatch){

        Optional<Item> optionalItem = repository.findById(itemToPatch.getId());

        Item item = optionalItem.get();
        item.setName(itemToPatch.getName());
        item.setPrice(itemToPatch.getPrice());
        item.setAmount(itemToPatch.getAmount());
        return item;
    }

    public ResponseEntity<List<Item>> bulkCreate(){
        List<Item> createdItems = repository.saveAll(Arrays.asList(
                new Item("Mace",10.5f,10,"wooden sticks"),
                new Item("Stick",56.5f,20,"wooden sticks"),
                new Item("spatula",1.5f,44,"wooden sticks")
        ));
        return new ResponseEntity<>(createdItems, HttpStatus.OK);
    }

}
