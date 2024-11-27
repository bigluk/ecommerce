package com.luciano.item.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.luciano.item.dtos.Item;
import com.luciano.item.exceptions.DuplicatedException;
import com.luciano.item.exceptions.NotFoundException;
import com.luciano.item.services.ItemService;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/item")
public class ItemController {
    

	private final ItemService itemService;
	


	@Operation(summary = "Find the item using barcode", 
        description = "Retrieve item description", 
		tags = { "Item" })
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Item found"),
		@ApiResponse(responseCode = "404", description = "Item not found") 
    })
	@GetMapping(value = "/find/{barcode}", produces = "application/json")
	public ResponseEntity<Item> findItemByBarcode(@PathVariable("barcode") Long barcode) throws NotFoundException {

		log.info("Received new request to find item with barcode: {}", barcode);
		
		Item item = itemService.fetchItemByBarcode(barcode);
		
		if (item == null){
			log.info("No item found with barcode: {}", barcode);
			throw new NotFoundException("No item found with barcode: " + barcode);
		}
		
		return new ResponseEntity<>(item, HttpStatus.OK);
    

	}
	

	
	@Operation(summary = "Insert a new item", 
	description = "Insert new item if it does not already exist", 
	tags = { "Item" })
 	@ApiResponses(value = {
	 @ApiResponse(responseCode = "200", description = "Item added"),
	 @ApiResponse(responseCode = "404", description = "Item not found") 
	})
	@PostMapping(value = "/add", produces = "application/json")
	public ResponseEntity<String> addItem(@Valid @RequestBody Item item) throws DuplicatedException {

		log.info("Received new request to insert item with barcode: {} ", item.getBarcode());
		
		
		if (itemService.checkIfItemAlreadyExist(item.getBarcode())){
			log.error("Item : {} already present", item);			
			throw new DuplicatedException("Cannot Proceed with insertion");
		}
		
		itemService.addItem(item);
		
		return new ResponseEntity<String>("{\"message\": \"Item added\"}", HttpStatus.CREATED);

	}
	


	@Operation(summary = "Update item", 
	description = "Update an item that already exist", 
	tags = { "Item" })
 	@ApiResponses(value = {
	 @ApiResponse(responseCode = "200", description = "Item updated"),
	 @ApiResponse(responseCode = "404", description = "Item to update not found") 
	})
	@PutMapping(value = "/update", produces = "application/json")
	public ResponseEntity<String> updateItem(@Valid @RequestBody Item item) throws NotFoundException {
		
		log.info("Received new request to update item with barcode: {}", item.getBarcode());
		
		if (itemService.checkIfItemAlreadyExist(item.getBarcode())){
			
			log.error("Item : {} not present", item);

			throw new NotFoundException("Cannot Proceed with update");
		}
		
		itemService.addItem(item);
		
		return new ResponseEntity<String>("{\"message\": \"Item updated\"}", HttpStatus.OK);

	}
	


	@DeleteMapping(value = "/delete/{barcode}", produces = "application/json" )
	public ResponseEntity<String> deleteArt(@PathVariable("codart") String barcode) {

		log.info("Received new request to delete item with barcode: {}", barcode);

		itemService.deleteItem(barcode);
		
		return new ResponseEntity<>("{\"message\": \"Item deleted\"}", HttpStatus.OK);
				
	}

	


}



