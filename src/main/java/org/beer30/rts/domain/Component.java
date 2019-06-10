package org.beer30.rts.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "component")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Component {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Environment environment;

    @Column
    private String name;

    @Column
    private String scmHash;

    @Column
    private Instant createDate;

//    @Column
    private String buildId;

    @Column
    private String sha256;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScmHash() {
        return scmHash;
    }

    public void setScmHash(String scmHash) {
        this.scmHash = scmHash;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return Objects.equals(id, component.id) &&
                Objects.equals(environment, component.environment) &&
                Objects.equals(name, component.name) &&
                Objects.equals(scmHash, component.scmHash) &&
                Objects.equals(createDate, component.createDate) &&
                Objects.equals(buildId, component.buildId) &&
                Objects.equals(sha256, component.sha256);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, environment, name, scmHash, createDate, buildId, sha256);
    }

    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", environment=" + environment +
                ", name='" + name + '\'' +
                ", scmHash='" + scmHash + '\'' +
                ", createDate=" + createDate +
                ", buildId='" + buildId + '\'' +
                ", sha256='" + sha256 + '\'' +
                '}';
    }
}
