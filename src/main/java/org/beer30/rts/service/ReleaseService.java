package org.beer30.rts.service;

import org.beer30.rts.domain.Deployable;
import org.beer30.rts.domain.Environment;
import org.beer30.rts.domain.Release;

public interface ReleaseService {
    public Release findRelease(Environment environment, Long id);

    public Release createRelease(Environment environment, String releaseLabel);

    public Release addDeployable(Environment environment, Release release, Deployable deployable);

 }
