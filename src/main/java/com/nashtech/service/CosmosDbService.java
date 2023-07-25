package com.nashtech.service;

import com.nashtech.exception.ResourceNotFoundException;
import com.nashtech.model.Car;
import com.nashtech.model.CarBrand;
import com.nashtech.model.ReactiveDataCars;
import com.nashtech.repository.CosmosDbRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class CosmosDbService implements CloudDataService {


    /**
     * The reactive repository for {@link ReactiveDataCars} entities
     * in Cosmos DB.
     * Used for performing CRUD operations and reactive data access.
     */
    @Autowired
    private CosmosDbRepository cosmosDbRepository;

    /**
     * Retrieves a Flux of cars with specified brand in reactive manner.
     * The Flux represents a stream of data that can be subscribed to for
     * continuous updates.
     *
     * @param brand The brand of cars to filter by.
     * @return A Flux of Car representing cars with the
     * specified brand.
     */
    public Flux<Car> getCarsByBrand(final String brand) {
        Flux<Car> allCarsOfBrand =
                cosmosDbRepository.getAllCarsByBrand(brand);
        return allCarsOfBrand
                .doOnComplete(() -> log.info("Received Data Successfully--"+allCarsOfBrand.getClass()))
                .switchIfEmpty(Flux.error(new ResourceNotFoundException()));
    }

    /**
     * Retrieves a Flux of distinct car brands in a reactive manner.
     * The Flux represents a stream of data that can be subscribed to for
     * continuous updates.
     * This method also prints the distinct brands to the console for
     * demonstration purposes.
     *
     * @return A Flux of CarBrand representing distinct car brands.
     */
    public Flux<CarBrand> getAllBrand() {
        Flux<CarBrand> distinctBrandsFlux =
                cosmosDbRepository.findDistinctBrands();
        return distinctBrandsFlux
                .doOnNext(brand ->
                        log.info("Distinct Brand: " + brand))
                .doOnError(error ->
                        log.error("Error occurred: " + error))
                .doOnComplete(() ->
                        log.info("Data processing completed."))
                .switchIfEmpty(Flux.error(new ResourceNotFoundException()));
    }
}