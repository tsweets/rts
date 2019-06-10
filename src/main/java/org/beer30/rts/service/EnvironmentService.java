package org.beer30.rts.service;

import org.beer30.rts.domain.Component;
import org.beer30.rts.domain.Environment;

public interface EnvironmentService {
    public Environment addEnvironment(String name);

    public Environment getEnvironment(Long environmentId);
}
