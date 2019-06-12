package org.beer30.rts.service.impl;

import org.beer30.rts.domain.Component;
import org.beer30.rts.domain.Deployable;
import org.beer30.rts.domain.Environment;
import org.beer30.rts.repository.DeployableRepository;
import org.beer30.rts.service.DeployableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Service
public class DeployableServiceImpl implements DeployableService {

    @Autowired
    DeployableRepository deployableRepository;



    @Override
    public Deployable addDeployable(Environment environment, String name, String buildId, String sha256) {
        Deployable deployable = new Deployable();

        deployable.setBuildId(buildId);
        deployable.setCreateDate(Instant.now());
        deployable.setEnvironment(environment);
        deployable.setName(name);
        deployable.setSha256(sha256);

        Deployable deployableSaved = deployableRepository.save(deployable);

        return deployableSaved;
    }

    @Override
    public Deployable getDeployable(Long deployableId) {
        Deployable deployableFound = deployableRepository.findById(deployableId).orElse(null);

        return deployableFound;
    }

    @Override
    public Deployable addComponent(Environment environment, Deployable deployable, Component component) {
        Set<Component> componentSet = deployable.getComponents();
        if (componentSet == null) {
            componentSet = new HashSet<Component>();
        }

        componentSet.add(component);
        deployable.setComponents(componentSet);
        Deployable deployableSaved = deployableRepository.save(deployable);

        return  deployableSaved;
    }
}
