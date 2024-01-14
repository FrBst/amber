// package org.keldeari.amber.configuration;

// import java.util.Collection;
// import java.util.Collections;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
// import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

// import com.mongodb.ConnectionString;
// import com.mongodb.MongoClientSettings;
// import com.mongodb.client.MongoClient;
// import com.mongodb.client.MongoClients;

// @Configuration
// @EnableMongoRepositories(basePackages = "org.keldeari.amber.repository")
// public class MongoConfiguration extends AbstractMongoClientConfiguration {

//     @Override
//     protected String getDatabaseName() {
//         return "admin";
//     }
    
//     @Override
//     public MongoClient mongoClient() {
//         ConnectionString connectionString = new ConnectionString(
//             "mongodb://root:example@localhost:27017/admin"
//         );
//         MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//             .applyConnectionString(connectionString)
//             .build();

//         return MongoClients.create(mongoClientSettings);
//     }

//     @Override
//     public Collection getMappingBasePackages() {
//         return Collections.singleton("org.keldeari.amber");
//     }
// }
