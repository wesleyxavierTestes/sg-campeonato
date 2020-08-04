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

    private static String ambiente = System.getenv("ambiente");

    @Bean
    public DataSource dataSource() {
        System.out.println("AMBIENTE "+ ambiente);
        DataSourceBuilder dataSource = DataSourceBuilder.create();

        dataSource.driverClassName("org.postgresql.Driver");
        dataSource.url("jdbc:postgresql://0.0.0.0:5432/sgcampeonato");
        dataSource.username("trainee");
        dataSource.password("123");

        return dataSource.build();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adpater = new HibernateJpaVendorAdapter();
        adpater.setDatabase(Database.POSTGRESQL);
        adpater.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        adpater.setGenerateDdl(true);
        adpater.setPrepareConnection(true);
        adpater.setShowSql(false);
        return adpater;
    }
}