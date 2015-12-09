package com.shoppingCart.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import com.shoppingCart.app.model.Customer;

import java.io.IOException;
import java.nio.charset.Charset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ShoppingCartApplication.class)
@WebAppConfiguration
public class ShoppingCartApplicationTests {

	private	Customer customer = new Customer();	
    private HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;
    private MockMvc mockMvc;
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
	            MediaType.APPLICATION_JSON.getSubtype(),
	            Charset.forName("utf8"));

	
	@Before
	public void setUp() {
		customer.setFirstName("Magalí");
		customer.setLastName("Kain");
		customer.setUsername("mkain");
		customer.setPassword("12345");
	}
	
	@Test
	public void addCustomer() {	
		try {
			String customerJson = json(customer);
			this.mockMvc.perform(post("/customers")
			            .contentType(contentType)
			            .content(customerJson))
			            .andExpect(status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
	    this.mappingJackson2HttpMessageConverter.write(
	            o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
	    return mockHttpOutputMessage.getBodyAsString();
	}
	
}
