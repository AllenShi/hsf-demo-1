package net.yarn.hsf.demo1;

import com.taobao.pandora.boot.PandoraBootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class HsfDemo1ConsumerApplication {

	public static void main(String[] args) {
		PandoraBootstrap.run(args);
        SpringApplication.run(HsfDemo1ConsumerApplication.class, args);
        PandoraBootstrap.markStartupAndWait();
	}

}

