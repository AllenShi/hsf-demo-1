package net.yarn.hsf.demo1.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.yarn.hsf.demo1.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/api/messages")
@Api(value = "Messaging Demo REST API", tags = { "RocketMQ" })
public class MessagingController {

	@Autowired
	private MessagingService messagingService;

	@RequestMapping(method = RequestMethod.POST, value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public void send(@RequestParam("topic") @ApiParam(value = "topic", required = true) String topic,
			@RequestParam(value = "tag", required = false) @ApiParam(value = "tag", required = false) String tag,
			@RequestParam("message") @ApiParam(value = "message", required = true) String message) {
		
		messagingService.send(topic, tag, message);
		
	}

}
