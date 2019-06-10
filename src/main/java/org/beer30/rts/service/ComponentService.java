package org.beer30.rts.service;

import org.beer30.rts.domain.Component;
import org.beer30.rts.domain.Environment;

public interface ComponentService {
    public Component addComponent(Environment environment, String name, String scmHash, String buildId, String sha256);

    public Component getComponent(Long componentId);
}
