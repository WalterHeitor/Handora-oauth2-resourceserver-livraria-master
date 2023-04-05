package br.com.zup.edu.livrariaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LivrariaclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaclientApplication.class, args);
	}

}
