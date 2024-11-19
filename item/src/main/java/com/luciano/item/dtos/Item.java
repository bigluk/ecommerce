package com.luciano.item.dtos;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class Item {
    
    @NotNull
	private String barcode;
    private String description;	
    private double netWeight;
	private String status;
	private LocalDateTime creationDate;
	
}
