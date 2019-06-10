package org.beer30.rts.repository;

import org.beer30.rts.domain.Deployable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeployableRepository extends JpaRepository<Deployable, Long> {
}
