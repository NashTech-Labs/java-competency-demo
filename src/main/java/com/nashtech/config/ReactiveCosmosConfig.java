package com.nashtech.config;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the connection to the Azure C
 * osmos DB for reactive data access.
 * Extends the {@link AbstractCosmosConfiguration} class to provide
 * necessary Cosmos DB configurations.
 */
@Configuration
public class ReactiveCosmosConfig extends AbstractCosmosConfiguration {

    /**
     * The URI of the Cosmos DB instance.
     */
    @Value("${cosmosdb.uri}")
    private String uri;

    /**
     * The access key for the Cosmos DB instance.
     */
    @Value("${cosmosdb.key}")
    private String key;

    /**
     * Creates a {@link CosmosClientBuilder} bean with the configured
     * URI and access key.
     * The CosmosClientBuilder is used to create instances of {
     * @link com.azure.cosmos.CosmosClient}.
     *
     * @return The CosmosClientBuilder instance.
     */
    @Bean
    public CosmosClientBuilder appCosmosClientBuilder() {
        return new CosmosClientBuilder()
                .key(key)
                .endpoint(uri);
    }

    /**
     * Creates a {@link CosmosConfig} bean with additional Cosmos DB
     * configurations.
     * In this case, it enables query metrics for monitoring and
     * performance analysis.
     *
     * @return The CosmosConfig instance with specified configurations.
     */
    @Bean
    public CosmosConfig cosmosConfig() {
        return CosmosConfig.builder()
                .enableQueryMetrics(true)
                .build();
    }

    /**
     * Specifies the name of the database to be used for the Cosmos
     * DB connection.
     * This method is overridden from the AbstractCosmosConfiguration.
     *
     * @return The name of the database ("CarFactory" in this case).
     */
    @Override
    protected String getDatabaseName() {
        return "CarFactory";
    }

}
