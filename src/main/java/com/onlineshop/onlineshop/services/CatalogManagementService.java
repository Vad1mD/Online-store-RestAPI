package com.onlineshop.onlineshop.services;

import com.onlineshop.onlineshop.Utils.Utils;
import com.onlineshop.onlineshop.dataModels.Catalog;
import com.onlineshop.onlineshop.persistance.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogManagementService {

    private CatalogRepository repository;

    private Utils utils;

    private HttpStatus status;

    @Autowired
    public CatalogManagementService(CatalogRepository repository, Utils utils) {
        this.repository=repository;
        this.utils=utils;
    }


    public List<Catalog> getAllCatalogs(){
        return repository.findAll();
    }


    public ResponseEntity<Catalog> getCatalogById(Long id,Long userId){

        if(!utils.validateUser(userId))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        Optional<Catalog> catalog = repository.findById(id);
        Catalog res = catalog.isPresent() ? catalog.get() : null;
        status = catalog.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(res, status);
    }


    public ResponseEntity<Catalog> addNewCatalog(Catalog newCatalog,Long userId){
        if(!utils.validateOwner(userId))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        if(newCatalog.getId() != 0 || newCatalog.getItems().stream().filter(e -> e.getId() != 0).count() > 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Catalog catalog = repository.save(newCatalog);

        return new ResponseEntity<>(catalog,HttpStatus.OK);
    }


    public ResponseEntity<Catalog> patchCatalog(Catalog catalogToPatch, Long userId){
        if(!utils.validateOwner(userId))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return updateCatalog(catalogToPatch);
    }


    public ResponseEntity<?> deleteCatalog(Long id,Long userId){

        if(utils.validateOwner(userId))
        {
            if(repository.existsById(id))
            {
                repository.deleteById(id);
                status = HttpStatus.OK;
            }else{
                status= HttpStatus.NOT_FOUND;
            }
        }else{
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<>(status);
    }


    private ResponseEntity<Catalog> updateCatalog(Catalog catalogToPatch){
        Optional<Catalog> catalogOptional = repository.findById(catalogToPatch.getId());

        if(!catalogOptional.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Catalog catalogFromDB = catalogOptional.get();
        catalogFromDB.setName(catalogToPatch.getName());
        catalogFromDB.setItems(catalogToPatch.getItems());

        Catalog updatedCatalog = repository.save(catalogFromDB);
        return new ResponseEntity<>(updatedCatalog,HttpStatus.OK);
    }

}
