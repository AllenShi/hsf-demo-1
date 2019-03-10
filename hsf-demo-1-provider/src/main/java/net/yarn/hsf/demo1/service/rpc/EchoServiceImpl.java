package net.yarn.hsf.demo1.service.rpc;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import com.alibaba.boot.hsf.annotation.HSFProvider;

import lombok.extern.slf4j.Slf4j;
import net.yarn.hsf.demo1.api.EchoService;

@Service
@HSFProvider(serviceGroup = "edas-demo1-provider", serviceInterface = EchoService.class)
@Slf4j
public class EchoServiceImpl implements EchoService {

	public String echo(String input) {
		log.debug("The argument input is {}", input);
		return input;
	}
	
	
	public String delayedReply(String input, int time, TimeUnit unit) throws InterruptedException {
		log.debug("The arguments input is {} and time is {} {}", input, time, unit.name());
		
		switch(unit) {
		case NANOSECONDS:
			Thread.sleep(0, time);
			break;
		case MICROSECONDS:
			Thread.sleep(0, time * 1000);
			break;
		case MILLISECONDS:
			Thread.sleep(time);
			break;
		case SECONDS:
			Thread.sleep(time * 1000);
			break;
		case MINUTES:
			Thread.sleep(time * 1000 * 60);
			break;
		case HOURS:
			Thread.sleep(time * 1000 * 60 * 60);
			break;
		case DAYS:
			Thread.sleep(time * 1000 * 60 * 60 * 24);
			break;
		}
		
		log.debug("After sleep");
		
		return input;
		
	}

}
