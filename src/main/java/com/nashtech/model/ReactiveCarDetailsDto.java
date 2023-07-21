package com.nashtech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;

/**
 * Represents a reactive data model for sending
 * details of the Car to the CosmosDB.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReactiveCarDetailsDto {

    /**
     * The card ID of the car.
     */
    private Integer cardId;

    /**
     * The brand of the car.
     */
    private String brand;

    /**
     * The model of the car.
     */
    @Version
    private String model;

    /**
     * The year of manufacture of the car.
     */
    private Long year;

    /**
     * The color of the car.
     */
    private String color;

    /**
     * The mileage of the car in kilometers.
     */
    private Double mileage;

    /**
     * The price of the car.
     */
    private Double price;
}