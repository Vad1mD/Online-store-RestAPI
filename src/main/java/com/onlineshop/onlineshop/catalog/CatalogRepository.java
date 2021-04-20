package com.onlineshop.onlineshop.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    List<Catalog> findAll();
    Optional<Catalog> findById(Long id);
    void deleteById(Long id);
    Catalog save(Catalog catalog);

    String findOwnerById(Long id);

}
