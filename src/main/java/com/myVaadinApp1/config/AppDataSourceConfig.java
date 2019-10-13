/**
 * 
 */
package com.myVaadinApp1.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sandi
 *
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.myVaadinApp1.repository" })
@PropertySource("classpath:/db.properties")
public class AppDataSourceConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppDataSourceConfig.class);
	
	@Autowired
	Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
		LOGGER.info(" ===== creating LocalContainerEntityManagerFactoryBean ==== ");
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.myVaadinApp1.entity");

		Properties jpaProperties = new Properties();

		// Configures the used database dialect. This allows Hibernate to create
		// SQL
		// that is optimized for the used database.
		jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));

		// Specifies the action that is invoked to the database when the
		// Hibernate
		// SessionFactory is created or closed.
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));

		// Configures the naming strategy that is used when Hibernate creates
		// new database objects and schema elements
		// jpaProperties.put("hibernate.ejb.naming_strategy",
		// env.getRequiredProperty("hibernate.ejb.naming_strategy"));

		// If the value of this property is true, Hibernate writes all SQL
		// statements to the console.
		jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));

		// If the value of this property is true, Hibernate will format the SQL
		// that is written to the console.
		jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));

		entityManagerFactoryBean.setJpaProperties(jpaProperties);
		LOGGER.info(" ===== in emf: ==== " + entityManagerFactoryBean);
		return entityManagerFactoryBean;
	}

	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		System.out.println(" ===== in tx mgr ===== ");
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		System.out.println(" ===== after tx mgr ===== ");
		return transactionManager;

	}
}
