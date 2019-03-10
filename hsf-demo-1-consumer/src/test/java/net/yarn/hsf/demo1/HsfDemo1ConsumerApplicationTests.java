package net.yarn.hsf.demo1;

import static junit.framework.TestCase.assertEquals;

import com.alibaba.boot.hsf.annotation.HSFConsumer;

import net.yarn.hsf.demo1.api.EchoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HsfDemo1ConsumerApplicationTests {
	
	
	@HSFConsumer
	private EchoService echoService;

	@Test
	public void contextLoads() {
	}
	
	
	@Test
    public void testMock() {
        EchoService mock = Mockito.mock(EchoService.class, AdditionalAnswers.delegatesTo(echoService));
        Mockito.when(mock.echo("")).thenReturn("beta");
        assertEquals("beta", mock.echo(""));
    }

}

