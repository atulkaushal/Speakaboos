package com.wfhackathon2022.speakaboos.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
		"com.wfhackathon2022.speakaboos.repository" }, entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class DatabaseConfig {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DatabaseConfig.class);

	@Bean(name = "dataSource", destroyMethod = "")
	public DataSource dataSource() throws IllegalArgumentException, NamingException, SQLException {
		// CODE_DEBT: externalize the values
		HikariConfig hkConfig = new HikariConfig();
		hkConfig.setJdbcUrl(
				"jdbc:sqlserver://speakaboos.database.windows.net:1433;database=PronunciationService;encrypt=true;trustServerCertificate=false;");
		hkConfig.setUsername("speakservice");
		hkConfig.setPassword("!lkHcktn");
		hkConfig.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		hkConfig.setMaximumPoolSize(10);
		hkConfig.setConnectionTestQuery("SELECT GETDATE()");
		return new HikariDataSource(hkConfig);
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(new String[] { "com.wfhackathon2022.speakaboos.entity" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setPersistenceUnitName("sql");
		em.setJpaProperties(additionalProperties());

		return em;
	}
	
	Properties additionalProperties() {
		//CODE_DEBT: Externalize
	    Properties properties = new Properties();
	    properties.setProperty("hibernate.hbm2ddl.auto", "none");
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
	    properties.setProperty("hibernate.show_sql", "true");
	    properties.setProperty("hibernate.format_sql", "true");
	       
	    return properties;
	}
	
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
	    JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(entityManagerFactory);

	    return transactionManager;
	}

}
