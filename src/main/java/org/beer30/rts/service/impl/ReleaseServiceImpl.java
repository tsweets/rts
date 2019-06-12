package org.beer30.rts.service.impl;

import org.beer30.rts.domain.Deployable;
import org.beer30.rts.domain.Environment;
import org.beer30.rts.domain.Release;
import org.beer30.rts.repository.ReleaseRepository;
import org.beer30.rts.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Service
public class ReleaseServiceImpl implements ReleaseService {

    @Autowired
    ReleaseRepository releaseRepository;

    @Override
    public Release findRelease(Environment environment, Long id) {
        Release release = releaseRepository.findReleaseByEnvironmentAndId(environment, id);
        return release;
    }

    @Override
    public Release createRelease(Environment environment, String releaseLabel) {
        Release release = new Release();
        release.setEnvironment(environment);
        release.setCreateDate(Instant.now());
        release.setReleaseLabel(releaseLabel);

        Release releaseSaved = releaseRepository.save(release);
        return releaseSaved;
    }

    @Override
    public Release addDeployable(Environment environment, Release release, Deployable deployable) {
        Set<Deployable> deployableSet = release.getDeployables();
        if (deployableSet == null) {
            deployableSet = new HashSet<Deployable>();
        }

        deployableSet.add(deployable);
        release.setDeployables(deployableSet);
        Release releaseSaved = releaseRepository.save(release);
        return releaseSaved;
    }


}
