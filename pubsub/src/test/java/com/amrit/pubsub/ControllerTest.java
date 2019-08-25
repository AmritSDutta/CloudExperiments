package com.amrit.pubsub;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PubsubApplication.class)
public class ControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private BuCreationEmailMessagingHandler buCreationEmailMessagingHandler;

    @Test
    public void basicTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/")
                                                              .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                                    .andExpect(status().isOk())
                                    .andExpect(content().string("Up"))
                                    .andReturn();

        assertEquals("Up",result.getResponse().getContentAsString());
    }


}
