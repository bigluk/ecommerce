package com.luciano.item.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.luciano.item.dtos.Item;
import com.luciano.item.entities.ItemEntity;
import com.luciano.item.repositories.ItemRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ItemService {


    private final ItemRepository itemRepository;
	
	private final ModelMapper modelMapper;



    public Item fetchItemByBarcode(String barcode) {

		ItemEntity item = itemRepository.findByBarcode(barcode);
		
		return modelMapper.map(item, Item.class);
	}


	public boolean checkIfItemAlreadyExist(String barcode) {

		return itemRepository.existsById(barcode);

	}


	@Transactional
	public void addItem(Item itemDto) {

		ItemEntity item = modelMapper.map(itemDto, ItemEntity.class);

		itemRepository.save(item);

	}


	@Transactional
	public void deleteItem(String barCode) {

		itemRepository.deleteById(barCode);

	}



}
