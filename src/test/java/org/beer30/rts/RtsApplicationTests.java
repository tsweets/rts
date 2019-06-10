package org.beer30.rts;

import org.beer30.rts.domain.Component;
import org.beer30.rts.domain.Environment;
import org.beer30.rts.repository.ComponentRepository;
import org.beer30.rts.repository.EnvironmentRepository;
import org.beer30.rts.service.ComponentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RtsApplicationTests {

    @Autowired
    EnvironmentRepository environmentRepository;

   /* @Autowired
    ComponentRepository componentRepository;
*/
    @Autowired
    ComponentService componentService;

	@Test
	public void contextLoads() {
        Environment environment = environmentRepository.findById(1l).orElse(null);
        Assert.assertNotNull(environment);
        Assert.assertEquals(1l, environment.getId().longValue());

/*
        Component component = new Component();
        component.setBuildId("BUILD 1234");
        component.setCreateDate(Instant.now());
        component.setEnvironment(environment);
        component.setName("Component 1");
        component.setScmHash("0a785c27f76f19987384e4abbf1b4e339d1b6f38");
*/
        Component component = componentService.addComponent(environment,"Component1","somehashnotreallybutbearwithme", "BUILD 1234","0a785c27f76f19987384e4abbf1b4e339d1b6f38");

        Assert.assertNotNull(component);

        /*Component componentSaved = componentRepository.save(component);
        Assert.assertNotNull(componentSaved);
*/
        Long componentId = component.getId();
        System.out.println("Component: " + component);

        Component componentFound = componentService.getComponent(componentId);
        Assert.assertNotNull(componentFound);
        System.out.println("Component Found: " + componentFound);


	}


}
