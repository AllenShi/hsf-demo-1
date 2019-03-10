package net.yarn.hsf.demo1.utest.rest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import net.yarn.hsf.demo1.api.EchoService;
import net.yarn.hsf.demo1.controller.EchoServiceController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(EchoServiceController.class)
public class EchoServiceControllerUnitTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private EchoService service;

	@Test
	public void givenResult_whenEcho_thenReturnString() throws Exception {

		String input = "my test input";
		when(service.echo(input)).thenReturn(input);

		mvc.perform(get("/api/v1/echoservice/echo").param("input", input).accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk()).andExpect(content().string(input));

	}

	@Test
	public void givenResult_whenDelayedReply_thenReturnString() throws Exception {

		String in = "my test input";
		int time = 10;
		final int timeOut = 3000;
		when(service.delayedReply(in, time, TimeUnit.SECONDS)).thenAnswer(new Answer<String>() {

			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {

				Object[] arguments = invocation.getArguments();

				String input = null;
				Integer time = null;
				TimeUnit tUnit = null;
				if (arguments != null && arguments.length == 3) {
					input = (String) arguments[0];
					time = (Integer) arguments[1];
					tUnit = (TimeUnit) arguments[2];
				}

				switch (tUnit) {
				case NANOSECONDS:
					if (time/(1000 * 1000) < timeOut) {
						Thread.sleep(time);
					} else {
						Thread.sleep(timeOut);
						throw new RuntimeException("The client time out occured!");
					}
					break;
					
				case MICROSECONDS:
					if (time/1000 < timeOut) {
						Thread.sleep(time);
					} else {
						Thread.sleep(timeOut);
						throw new RuntimeException("The client time out occured!");
					}
					break;
					
				case MILLISECONDS:
					if (time < timeOut) {
						Thread.sleep(time);
					} else {
						Thread.sleep(timeOut);
						throw new RuntimeException("The client time out occured!");
					}
					break;
					
				case SECONDS:
					if (time * 1000 < timeOut) {
						Thread.sleep(time);
					} else {
						Thread.sleep(timeOut);
						throw new RuntimeException("The client time out occured!");
					}
					break;
					
				case MINUTES:
					if (time * 1000 * 60 < timeOut) {
						Thread.sleep(time * 1000 * 60);
					} else {
						Thread.sleep(timeOut);
						throw new RuntimeException("The client time out occured!");
					}
					break;
					
				case HOURS:
					if (time * 1000 * 60 * 60 < timeOut) {
						Thread.sleep(time * 1000 * 60 * 60);
					} else {
						Thread.sleep(timeOut);
						throw new RuntimeException("The client time out occured!");
					}
					break;
					
				case DAYS:
					if (time * 1000 * 60 * 60 * 24 < timeOut) {
						Thread.sleep(time * 1000 * 60 * 60 * 24);
					} else {
						Thread.sleep(timeOut);
						throw new RuntimeException("The client time out occured!");
					}
					break;
				}

				return input;
			}

		});

		mvc.perform(get("/api/v1/echoservice/reply/afterseconds/" + time).param("input", in).accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk()).andExpect(content().string(in));

	}

}
