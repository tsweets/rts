package org.beer30.rts.service;

import org.beer30.rts.domain.Component;
import org.beer30.rts.domain.Deployable;
import org.beer30.rts.domain.Environment;

public interface DeployableService {
    public Deployable addDeployable(Environment environment, String name, String buildId, String sha256);

    public Deployable getDeployable(Long deployableId);

    public Deployable addComponent(Environment environment, Deployable deployable, Component component);
}
