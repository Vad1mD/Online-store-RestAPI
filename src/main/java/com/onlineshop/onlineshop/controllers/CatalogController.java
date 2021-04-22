package com.onlineshop.onlineshop.controllers;

import com.onlineshop.onlineshop.dataModels.Catalog;
import com.onlineshop.onlineshop.services.CatalogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/{id}/{user}")
    public ResponseEntity<Catalog> getCatalogById(@PathVariable Long id, @PathVariable Long user){
        return catalogManagementService.getCatalogById(id,user);
    }


    @PostMapping("/{user}")
    public ResponseEntity<Catalog> addNewCatalog(@RequestBody Catalog newCatalog, @PathVariable Long user){
        return catalogManagementService.addNewCatalog(newCatalog,user);
    }

    @PatchMapping("/{user}")
    public ResponseEntity<Catalog> patchCatalog(@RequestBody Catalog catalogToPatch, @PathVariable Long user){
        return catalogManagementService.patchCatalog(catalogToPatch, user);
    }


    @DeleteMapping("/{id}/{user}")
    public ResponseEntity<?> deleteCatalog(@PathVariable Long id, @PathVariable Long user){
        return catalogManagementService.deleteCatalog(id,user); // chekc if not exists
    }


}
