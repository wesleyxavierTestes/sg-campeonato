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
        DataSourceBuilder dataSource = DataSourceBuilder.create();

        if (ambiente != null)
            dataSourcePostgreSql(dataSource);
        else
            dataSourceH2(dataSource);

        return dataSource.build();
    }

    private void dataSourceH2(DataSourceBuilder dataSource) {
        dataSource.driverClassName("org.h2.Driver");
        dataSource.url("jdbc:h2:mem:dbcampeonato");
        dataSource.username("sa");
        dataSource.password("sa");
    }

    private void dataSourcePostgreSql(DataSourceBuilder dataSource) {
        dataSource.driverClassName("org.postgresql.Driver");
        dataSource.url("jdbc:postgresql://0.0.0.0:5432/sgcampeonato");
        dataSource.username("trainee");
        dataSource.password("123");
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adpater = new HibernateJpaVendorAdapter();
        
        adpater.setGenerateDdl(true);
        adpater.setPrepareConnection(true);
        adpater.setShowSql(false);

        if (ambiente != null)
            return jpaVendorAdapterPostgreSql(adpater);
        else
            return jpaVendorAdapterH2(adpater);
    }

    private JpaVendorAdapter jpaVendorAdapterH2(HibernateJpaVendorAdapter adpater) {
        adpater.setDatabase(Database.H2);
        adpater.setDatabasePlatform("org.hibernate.dialect.H2Dialect");

        return adpater;
    }

    private JpaVendorAdapter jpaVendorAdapterPostgreSql(HibernateJpaVendorAdapter adpater) {
        adpater.setDatabase(Database.POSTGRESQL);
        adpater.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");

        return adpater;
    }
}