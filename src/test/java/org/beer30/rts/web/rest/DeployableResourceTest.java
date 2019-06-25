package org.beer30.rts.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.beer30.rts.RtsApplication;
import org.beer30.rts.domain.Deployable;
import org.beer30.rts.domain.Environment;
import org.beer30.rts.service.EnvironmentService;
import org.hamcrest.Matchers;
import org.junit.Ignore;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RtsApplication.class)
@AutoConfigureMockMvc
public class DeployableResourceTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvironmentService environmentService;

    @Ignore
    @Test
    public void testDeployable() throws Exception {
        Environment environment = new Environment();
        environment.setId(1l);

        Deployable deployable = new Deployable();
        deployable.setEnvironment(environment);
        deployable.setBuildId("Build ID 1223");
        deployable.setName("Deployable A");
        deployable.setSha256("SOMESHA256");


        mockMvc.perform(post("/api/deployables")
                .content(asJsonString(deployable))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/api/deployables/1")
            .contentType(
                    MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
              //  .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Deployable A")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}