package net.yarn.hsf.demo1.controller;


import java.util.concurrent.TimeUnit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import net.yarn.hsf.demo1.api.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/echoservice")
@Api(value = "HSF Demo Service REST API", tags = {"HSF"})
@Slf4j
public class EchoServiceController {
	
	@Autowired
	private EchoService echoService;
	
	@ApiOperation(value = "Get echo string", consumes = "", produces = MediaType.TEXT_PLAIN_VALUE)
	@ApiResponses(value = {@ApiResponse(code = 414, message = "The URI is too long."), 
			@ApiResponse(code = 500, message = "The server side error happened.")})
	@RequestMapping(method = RequestMethod.GET, value = "/echo", produces = MediaType.TEXT_PLAIN_VALUE)
	public String echo(@RequestParam(name = "input", required = true) @ApiParam(value = "input", required = true) String input) {
		log.debug("input is {}", input);
		
		return echoService.echo(input);
	}
	
	
	@ApiOperation(value = "Get reply after delayed seconds", consumes = "", produces = MediaType.TEXT_PLAIN_VALUE)
	@ApiResponses(value = {@ApiResponse(code = 414, message = "The URI is too long."), 
			@ApiResponse(code = 500, message = "The server side error happened.")})
	@RequestMapping(method = RequestMethod.GET, value = "/reply/afterseconds/{second}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyWithDelayedSeconds(@RequestParam(name = "input", required = true) @ApiParam(value = "input", required = true) String input,
			@PathVariable(name = "second", required = true) @ApiParam(value = "second", required = true) int second) {
		log.debug("The parameters input is {} and time is {} seconds", input, second);
		
		try {
			return echoService.delayedReply(input, second, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			log.debug("The exception is thrown", e);
			throw new RuntimeException("The request was interrupted!", e);
		}
	}

}
