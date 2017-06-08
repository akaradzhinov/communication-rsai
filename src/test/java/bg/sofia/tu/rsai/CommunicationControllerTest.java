package bg.sofia.tu.rsai;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import bg.sofia.tu.rsai.communication.CommunicationController;
import bg.sofia.tu.rsai.communication.CommunicationService;
import ch.qos.logback.classic.spi.ILoggingEvent;


@RunWith(SpringRunner.class)
@WebMvcTest(value=CommunicationController.class, secure=false)
public class CommunicationControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CommunicationService communicationService;

	@Test
	public void test() throws Exception {
		
		String content ="{ \"moduleName\":\"display\", \"value\": { \"gear\":4, \"rpm\":4500 } }";

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/communication/test")
				.accept(MediaType.APPLICATION_JSON).content(content)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(400, response.getStatus());
		assertEquals("your request was pretty bad",
				response.getContentAsString());

		

	}
	
	@Test
	public void createResponse() throws Exception {
		
		String content ="{ \"moduleName\":\"display\", \"value\": { \"gear\":4, \"rpm\":4500 } }";

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/communication/send")
				.accept(MediaType.APPLICATION_JSON).content(content)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		
		if(response.getStatus() == 200) {
			assertEquals(content, response.getContentAsString());
		} 

	}
}
