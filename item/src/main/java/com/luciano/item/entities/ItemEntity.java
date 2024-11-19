package com.luciano.item.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
 

@Entity
@Table(name = "ITEM")
@Data
@NoArgsConstructor
public class ItemEntity  implements Serializable {
	
	private static final long serialVersionUID = 291353626011036772L;
	
	@Id
	@Column(name = "barcode")
	@Size(min = 5, max = 20, message = "barcode must be between 5 and 20 digits")
	@NotNull(message = "barcode cannot be null")
	private String barcode;
	
	@Column(name = "description")
	@Size(min = 6, max = 80, message = "{Size.Articoli.descrizione.Validation}")
	private String description;	
	
	@Column(name = "netWeight")
	@Min(value = (long) 0.01, message = "")
	private double netWeight;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "creationDate")
	private LocalDateTime creationDate;
	
	
}
