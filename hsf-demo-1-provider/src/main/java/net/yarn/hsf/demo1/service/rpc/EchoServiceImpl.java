package net.yarn.hsf.demo1.service.rpc;

import org.springframework.stereotype.Service;

import com.alibaba.boot.hsf.annotation.HSFProvider;

import net.yarn.hsf.demo1.api.EchoService;

@Service
@HSFProvider(serviceGroup = "edas-demo1-provider", serviceInterface = EchoService.class)
public class EchoServiceImpl implements EchoService {

	public String echo(String input) {
		return input;
	}

}
