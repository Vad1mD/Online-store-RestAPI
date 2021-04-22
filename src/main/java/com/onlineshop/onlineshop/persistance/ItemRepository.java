package com.onlineshop.onlineshop.persistance;

import com.onlineshop.onlineshop.dataModels.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAll();
    List<Item> findByIdIn(List<Long> id);
    Optional<Item> findById(Long id);
    Item save(Item item);
    boolean existsById(Long id);
    void deleteById(Long id);
}
