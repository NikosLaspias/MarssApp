package com.marsops.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;

    // Custom constructor για χρήση στα tests
    public Resource(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}