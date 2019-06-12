package org.beer30.rts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.beer30.rts.domain.Component;
import org.beer30.rts.domain.Deployable;
import org.beer30.rts.domain.Environment;
import org.beer30.rts.domain.Release;
import org.beer30.rts.domain.enums.ComponentType;
import org.beer30.rts.repository.EnvironmentRepository;
import org.beer30.rts.service.ComponentService;
import org.beer30.rts.service.DeployableService;
import org.beer30.rts.service.ReleaseService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RtsApplicationTests {

    @Autowired
    EnvironmentRepository environmentRepository;

    @Autowired
    ComponentService componentService;

    @Autowired
    DeployableService deployableService;

    @Autowired
    ReleaseService releaseService;


	@Test
    @Transactional
	public void contextLoads() {
        Environment environment = environmentRepository.findById(1l).orElse(null);
        Assert.assertNotNull(environment);
        Assert.assertEquals(1l, environment.getId().longValue());

        Component component = componentService.addComponent(environment,"Component 1","b278cafd5b8", "BUILD 1234", ComponentType.BUILDABLE, "e8620b5f8e931b9a8c5b4c07ae7c0952330e7b208d9ce4766bb98ac4b3bbed37");

        Assert.assertNotNull(component);
        Long componentId = component.getId();
        System.out.println("Component: " + component);

        Component componentFound = componentService.getComponent(componentId);
        Assert.assertNotNull(componentFound);
        System.out.println("Component Found: " + componentFound);

        Component component2 = componentService.addComponent(environment,"Component 2","267f27e3971", "BUILD 28188", ComponentType.BUILDABLE, "6cc7a1ce03e4d9fcfce490b39209c23dd48d49905ce96046fd1704a04426ada3");
        Component component3 = componentService.addComponent(environment,"Component 3","f46fdfa0593", "BUILD 15", ComponentType.BUILDABLE, "3f15e52ae9be914ea808a50a93284dd3aeac05b4fc5e3c3fae96d5c97552f204");
        Component component4 = componentService.addComponent(environment,"jackson-annotations",null, null, ComponentType.THIRD_PARTY, "d5b90213-52f8-4429-b3c7-ed582af929d2");

        Deployable deployable = deployableService.addDeployable(environment,"Deployable A","Build 114", "dcb997cc2bfd396426f0d6c72135706519e9f66ee25a0008cf78a23d84e7543b");
        Assert.assertNotNull(deployable);

        deployable = deployableService.addComponent(environment,deployable,component);
        deployable = deployableService.addComponent(environment,deployable,component2);
        Assert.assertNotNull(deployable);

        Set<Component> components = deployable.getComponents();
        Assert.assertNotNull(components);
        Assert.assertEquals(2, components.size());

        Release release = releaseService.createRelease(environment,"SAM Skyline Release v1.2.3");
        Assert.assertNotNull(release);

        Deployable deployable2 = deployableService.addDeployable(environment,"Deployable B","Build 109", "24d613a3d6d65e6aca56735198d0f43a7276a8cbb3694bda877d77cd2e234cdf");
        deployable2 = deployableService.addComponent(environment, deployable2,component3);
        deployable2 = deployableService.addComponent(environment, deployable2,component4);

        release = releaseService.addDeployable(environment,release,deployable);
        Assert.assertNotNull(release);

        release = releaseService.addDeployable(environment,release,deployable2);

        Set<Deployable> deployables = release.getDeployables();
        Assert.assertNotNull(deployables);
        Assert.assertEquals(2, deployables.size());

        // Get Release from Database
        Release releaseFound = releaseService.findRelease(environment,release.getId());
        Assert.assertNotNull(releaseFound);

        // Print JSON Manifest
        ObjectMapper mapper = new ObjectMapper();
        String manifest = null;
        try {
            manifest = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(releaseFound);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(manifest);
        System.out.println(manifest);
    }


}
