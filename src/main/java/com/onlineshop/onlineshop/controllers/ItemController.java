package com.onlineshop.onlineshop.controllers;

import com.onlineshop.onlineshop.dataModels.Item;
import com.onlineshop.onlineshop.services.ItemManagementService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping
    public ResponseEntity<List<Item>> getAllItemsInTheCatalog(){
        return itemManagementService.getAllItemsInTheCatalog();
    }


    @GetMapping("/{id}/{user}")
    public ResponseEntity<Item> getItem(@PathVariable Long id, @PathVariable Long user){
        return itemManagementService.getItem(id,user);
    }


    @PostMapping("/{user}")
    public ResponseEntity<Item> addItem(@RequestBody Item newItem, @PathVariable Long user){
        return itemManagementService.addItem(newItem,user);
    }


    @PatchMapping("/{user}")
    public ResponseEntity<Item> patchItem(@PathVariable Long user, @RequestBody Item itemToPatch){
        return itemManagementService.patchItem(user, itemToPatch);
    }


    @DeleteMapping("/{id}/{user}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id, @PathVariable Long user){
        return new ResponseEntity<>(itemManagementService.deleteItem(id,user));
    }


    @PostMapping("/purchase/{user}")
    public ResponseEntity<List<Item>> purchaseItem(@RequestBody List<Item> itemsToPurchase, @PathVariable Long user){
        return itemManagementService.purchaseItem(itemsToPurchase,user);
    }

}
