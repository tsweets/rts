package org.beer30.rts.repository;

import org.beer30.rts.domain.Environment;
import org.beer30.rts.domain.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {

    public Release findReleaseByEnvironmentAndId(Environment environment, Long Id);
}
