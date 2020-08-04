package com.sgcampeonato.infra.data;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataSourceConfigure {

    private String ambiente = System.getenv("ambiente");

    @Bean
    public DataSource dataSource() {
        System.out.println("AMBIENTE " + ambiente);
        DataSourceBuilder dataSource = DataSourceBuilder.create();

        if (ambiente != null) {
            dataSource.driverClassName("org.postgresql.Driver");
            dataSource.url("jdbc:postgresql://0.0.0.0:5432/sgcampeonato");
            dataSource.username("trainee");
            dataSource.password("123");
        } else {
            dataSource.driverClassName("org.h2.Driver");
            dataSource.url("jdbc:h2:mem:dbcampeonato");
            dataSource.username("sa");
            dataSource.password("sa");
        }
        return dataSource.build();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adpater = new HibernateJpaVendorAdapter();
        if (ambiente != null) {
            adpater.setDatabase(Database.POSTGRESQL);
            adpater.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        } else {
            adpater.setDatabase(Database.H2);
            adpater.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        }
        adpater.setGenerateDdl(true);
        adpater.setPrepareConnection(true);
        adpater.setShowSql(false);
        return adpater;
    }
}