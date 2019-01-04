package ir.iut.adb;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableNeo4jRepositories(basePackages = "ir.iut.adb.neo4j", transactionManagerRef = "neo4jTransactionManager")
@SpringBootApplication
@EnableTransactionManagement
public class Application {

    @Value("${spring.data.neo4j.uri}")
    private String databaseUrl;

    @Value("${spring.data.neo4j.username}")
    private String userName;

    @Value("${spring.data.neo4j.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public org.neo4j.ogm.config.Configuration configuration() {
        //        configuration.driverConfiguration().setDriverClassName(EmbeddedDriver.class.getName());
        return new Configuration.Builder()
                .uri(databaseUrl)
                .credentials(userName, password)
                .build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(configuration(), "ir.iut.adb.neo4j.domain");
    }

    @Bean
    public Neo4jTransactionManager neo4jTransactionManager() {
        return new Neo4jTransactionManager(sessionFactory());
    }
}
