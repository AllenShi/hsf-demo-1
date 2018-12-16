package net.yarn.hsf.demo1.config;

import com.alibaba.boot.hsf.annotation.HSFConsumer;

import net.yarn.hsf.demo1.api.EchoService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HSFConfig {
	
	@HSFConsumer(serviceGroup = "edas-demo1-provider", serviceVersion = "0.0.1", addressWaitTime = 20000)
	private EchoService echoService;

}
