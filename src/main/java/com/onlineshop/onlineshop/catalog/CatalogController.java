package com.onlineshop.onlineshop.catalog;

import com.onlineshop.onlineshop.item.Item;
import com.onlineshop.onlineshop.item.ItemController;
import com.onlineshop.onlineshop.user.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/catalog")
public class CatalogController {

    private CatalogManagementService catalogManagementService;

    @Autowired
    public CatalogController(CatalogManagementService catalogManagementService){
        this.catalogManagementService=catalogManagementService;
    }

    @GetMapping
    public List<Catalog> getAllCatalogs(){
        return catalogManagementService.getAllCatalogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalog> getCatalogById(@PathVariable Long id){
        Catalog catalog = catalogManagementService.getCatalogById(id);
        return new ResponseEntity<>(catalog,HttpStatus.OK);
    }

    @GetMapping("/{id}/owner")
    public String getCatalogOwners(@PathVariable Long id){
        return catalogManagementService.getCatalogOwners(id);
    }

    @PostMapping
    public Catalog addNewCatalog(@RequestBody Catalog newCatalog){
        return catalogManagementService.addNewCatalog(newCatalog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCatalog(@PathVariable Long id){
        return catalogManagementService.deleteCatalog(id);
    }


    @GetMapping("/bulkcreate")
    public ResponseEntity<List<Catalog>> bulkCreate(){
        List<Catalog> bulkCatalog = catalogManagementService.bulkCreate();
        return new ResponseEntity<>(bulkCatalog, HttpStatus.OK);
    }

}
