package com.onlineshop.onlineshop.catalog;

import com.onlineshop.onlineshop.item.ItemController;
import com.onlineshop.onlineshop.user.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogManagementService {

    private CatalogRepository repository;

    @Autowired
    public CatalogManagementService(CatalogRepository repository) {this.repository=repository;}

    public List<Catalog> getAllCatalogs(){
        return repository.findAll();
    }

    public Catalog getCatalogById(Long id){
        Optional<Catalog> catalog = repository.findById(id);
        return catalog.isPresent() ? catalog.get() : null;
    }

    public String getCatalogOwners(Long id){
        return repository.findOwnerById(id);
    }

    public Catalog addNewCatalog(Catalog newCatalog){
        return repository.save(newCatalog);
    }

    public ResponseEntity<?> deleteCatalog(Long id){
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public List<Catalog> bulkCreate(){
        return repository.saveAll(Arrays.asList(
                new Catalog("wood", 1234),
                new Catalog("Stone", 11541654),
                new Catalog("lava", 68842344)
        ));
    }


}
