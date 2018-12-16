package net.yarn.hsf.demo1.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.yarn.hsf.demo1.api.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/echoservice")
@Api(value = "HSF Demo Service REST API", tags = {"HSF"})
public class EchoServiceController {
	
	@Autowired
	private EchoService echoService;
	
	@ApiOperation(value = "Get echo string", consumes = "", produces = MediaType.TEXT_PLAIN_VALUE)
	@ApiResponses(value = {@ApiResponse(code = 414, message = "The URI is too long."), 
			@ApiResponse(code = 500, message = "The server side error happened.")})
	@RequestMapping(method = RequestMethod.GET, value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public String echo(@RequestParam("input") @ApiParam(value = "input", required = true) String input) {
		return echoService.echo(input);
	}

}
