package org.beer30.rts.service;

import org.beer30.rts.domain.Component;
import org.beer30.rts.domain.Environment;
import org.beer30.rts.domain.enums.ComponentType;

public interface ComponentService {
    public Component addComponent(Environment environment, String name, String scmHash, String buildId,ComponentType componentType, String componentReference);

    public Component getComponent(Long componentId);
}
