package io.dandan.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerException;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

@Component("io.dandan")
@EnableAutoConfiguration
@SpringBootApplication
@ImportResource({"message-producer.xml"})
public class Application implements CommandLineRunner,EmbeddedServletContainerCustomizer{

	
	@Override
	public void run(String... args) throws Exception {
		while (true) {
			
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	/**
	 * 设置内置服务容器的端口，如Jetty与Tomcat
	 */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(9099);
	}

}
