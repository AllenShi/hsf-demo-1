package net.yarn.hsf.demo1.service;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.yarn.hsf.demo1.service.BookService.HystrixClientFallbackFactory;

@FeignClient(name = "hello", url = "${service.bookstore.url}", fallbackFactory = HystrixClientFallbackFactory.class)
public interface BookService {
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/recommended")
	String readingList();
	
	@Component 
	static class HystrixClientFallbackFactory implements FallbackFactory<BookService> {
		@Override
		public BookService create(Throwable cause) {
			return new BookService() {
				@Override
				public String readingList() {
					return "Cloud Native Java (O'Reilly)";
				}
			};
		}
	}

}





