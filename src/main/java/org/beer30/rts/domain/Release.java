package org.beer30.rts.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "release")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Release {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Environment environment;

    @Column
    private Instant createDate;

    @Column
    private String releaseLabel;

    @ManyToMany
    @JoinTable(name = "release_deployable", joinColumns = @JoinColumn(name = "release_id"), inverseJoinColumns = @JoinColumn(name = "deployable_id"))
    private Set<Deployable> deployables;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Set<Deployable> getDeployables() {
        return deployables;
    }

    public void setDeployables(Set<Deployable> deployables) {
        this.deployables = deployables;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getReleaseLabel() {
        return releaseLabel;
    }

    public void setReleaseLabel(String releaseLabel) {
        this.releaseLabel = releaseLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Release release = (Release) o;
        return Objects.equals(id, release.id) &&
                Objects.equals(environment, release.environment) &&
                Objects.equals(createDate, release.createDate) &&
                Objects.equals(releaseLabel, release.releaseLabel) &&
                Objects.equals(deployables, release.deployables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, environment, createDate, releaseLabel, deployables);
    }

    @Override
    public String toString() {
        return "Release{" +
                "id=" + id +
                ", environment=" + environment +
                ", createDate=" + createDate +
                ", releaseLabel='" + releaseLabel + '\'' +
                ", deployables=" + deployables +
                '}';
    }
}
