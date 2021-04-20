package com.onlineshop.onlineshop.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog/item")
public class ItemController {

    private ItemManagementService itemManagementService;

    @Autowired
    ItemController(ItemManagementService itemManagementService){
        this.itemManagementService=itemManagementService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id){
        Item item = itemManagementService.getItem(id);
        if(item == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @PostMapping
    public Item addItem(@RequestBody Item item){
        return itemManagementService.addItem(item);
    }
    @GetMapping
    public List<Item> getAllItemsInTheCatalog(){
        return itemManagementService.getAllItemsInTheCatalog();
    }

    @PatchMapping
    public Item editItem(@RequestBody Item itemToPatch){
        return itemManagementService.updateItem(itemToPatch);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id){
        itemManagementService.deleteItem(id);
    }

    @GetMapping("/bulkcreate")
    public ResponseEntity<List<Item>> bulkCreate(){
        return itemManagementService.bulkCreate();
    }
}
