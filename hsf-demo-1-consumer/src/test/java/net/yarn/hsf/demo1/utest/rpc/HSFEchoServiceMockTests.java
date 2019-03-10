package net.yarn.hsf.demo1.utest.rpc;

import net.yarn.hsf.demo1.api.EchoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HSFEchoServiceMockTests.class})
public class HSFEchoServiceMockTests {
	
    @MockBean
	private EchoService echoService;
	
	
	@Test
    public void testEchoServiceWithMockAPI() {
		EchoService mock = Mockito.mock(EchoService.class);
        when(mock.echo("")).thenReturn("beta");
        assertEquals("beta", mock.echo(""));
    }
	
	@Test
    public void testEchoServiceWithMockBean() {
        when(echoService.echo("")).thenReturn("beta");
        assertEquals("beta", echoService.echo(""));
    }

}

