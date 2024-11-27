package com.luciano.item.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luciano.item.entities.ItemEntity;

@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, String>{
    
	@Query(value = "SELECT * FROM ITEM WHERE BARCODE = :barcode", nativeQuery = true)
    ItemEntity findByBarcode(Long barcode);
	
}
