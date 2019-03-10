package net.yarn.hsf.demo1.controller;

import java.net.URI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.yarn.hsf.demo1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/v1/users")
@Api(value = "Hystrix and Feign Demo Service REST API", tags = {"Hystrix", "Feign"})
public class UserReadingController {
	
	@Autowired
	private BookService bookService;

	@ApiOperation(value = "Get books to be read in case 1", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	@ApiResponses(value = {@ApiResponse(code = 414, message = "The URI is too long."), 
			@ApiResponse(code = 500, message = "The server side error happened.")})
	@RequestMapping(method = RequestMethod.GET, value = "/{user}/toberead1", produces = MediaType.TEXT_PLAIN_VALUE)
	public String readingList1(@PathVariable(name = "user", required = true) @ApiParam(value = "user", required = true) String user) {
		RestTemplate restTemplate = new RestTemplate();
		URI uri = URI.create("http://localhost:9080/recommended");

		return restTemplate.getForObject(uri, String.class);
	}
	
	@ApiOperation(value = "Get books to be read in case 2", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
	@ApiResponses(value = {@ApiResponse(code = 414, message = "The URI is too long."), 
			@ApiResponse(code = 500, message = "The server side error happened.")})
	@RequestMapping(method = RequestMethod.GET, value = "/{user}/toberead2", produces = MediaType.TEXT_PLAIN_VALUE)
	public String readingList2(@PathVariable(name = "user", required = true) @ApiParam(value = "user", required = true) String user) {
		return bookService.readingList();
	}

}
