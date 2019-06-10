package org.beer30.rts.service.impl;

import org.beer30.rts.domain.Component;
import org.beer30.rts.domain.Environment;
import org.beer30.rts.repository.ComponentRepository;
import org.beer30.rts.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ComponentServiceImpl implements ComponentService {

    @Autowired
    ComponentRepository componentRepository;

    @Override
    public Component addComponent(Environment environment, String name, String scmHash, String buildId, String sha256) {
        Component component = new Component();
        component.setBuildId(buildId);
        component.setCreateDate(Instant.now());
        component.setEnvironment(environment);
        component.setName(name);
        component.setScmHash(scmHash);
        component.setSha256(sha256);

        Component componentSaved = componentRepository.save(component);

        return componentSaved;
    }

    @Override
    public Component getComponent(Long componentId) {
        Component componentFound = componentRepository.findById(componentId).orElse(null);
        return componentFound;
    }
}
