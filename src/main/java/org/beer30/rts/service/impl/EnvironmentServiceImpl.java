package org.beer30.rts.service.impl;

import org.beer30.rts.domain.Component;
import org.beer30.rts.domain.Environment;
import org.beer30.rts.repository.ComponentRepository;
import org.beer30.rts.repository.EnvironmentRepository;
import org.beer30.rts.service.ComponentService;
import org.beer30.rts.service.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

    @Autowired
    EnvironmentRepository environmentRepository;
/*


    @Override
    public Component getComponent(Long componentId) {
        Component componentFound = componentRepository.findById(componentId).orElse(null);
        return componentFound;
    }
*/
    @Override
    public Environment addEnvironment(String name) {
        Environment environment = new Environment();
        environment.setName(name);

        Environment environmentSaved = environmentRepository.save(environment);
        return environmentSaved;
    }

    @Override
    public Environment getEnvironment(Long environmentId) {
        Environment environmentFound = environmentRepository.findById(environmentId).orElse(null);
        return environmentFound;
    }
}
