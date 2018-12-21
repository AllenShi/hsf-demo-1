package net.yarn.hsf.demo1.startup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Order(value = 1)
public class StartupBean1 implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		
		log.debug("Start to run inside StartupBean1");
		
	}

}
