package com.luciano.auth.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_credential")
public class UserCredential implements Serializable {

	private static final long serialVersionUID = 291353626011036772L;

    @Id
    private String username;
    private String password;
    private Boolean isAccountDisabled;
    private String authority;
    
}
