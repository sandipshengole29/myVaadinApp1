package com.myVaadinApp1.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.myVaadinApp1"})
public class MyVaadinDemoApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(MyVaadinDemoApplication.class);
	
	public static void main(String[] args) {
		LOGGER.info("==================== starting MyVaadinDemoApplication ========================================");
		SpringApplication.run(MyVaadinDemoApplication.class, args);
	}

	/*
	 * @Bean public CommandLineRunner loadData(ContactRepository contactRepository)
	 * { return (args) -> { // save a couple of contacts for demo
	 * contactRepository.save(new Contact("abc", "def", "abc.def@yopmail.com",
	 * "6365272943")); contactRepository.save(new Contact("ghi", "jkl",
	 * "ghi.jkl@yopmail.com", "(636) 527-2943")); contactRepository.save(new
	 * Contact("mno", "pqr", "mno.pqr@yopmail.com", "(314) 527 2943"));
	 * contactRepository.save(new Contact("stu", "vwx", "stu.vwx@yopmail.com",
	 * "404-789-1456")); }; }
	 */
}
