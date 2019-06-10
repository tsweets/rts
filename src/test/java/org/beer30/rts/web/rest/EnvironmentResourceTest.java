package org.beer30.rts.web.rest;

import org.beer30.rts.RtsApplication;
import org.beer30.rts.service.EnvironmentService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RtsApplication.class)
@AutoConfigureMockMvc
public class EnvironmentResourceTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvironmentService environmentService;

    @Test
    public void getEnv1() throws Exception {
        mockMvc.perform(get("/api/environments/1")
            .contentType(
                    MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
              //  .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Test Env 1")));

    }

}