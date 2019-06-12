package org.beer30.rts.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "deployable")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Deployable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Environment environment;

    @Column
    private Instant createDate;

    @Column
    private String name;

    @Column
    private String buildId;

    @Column
    private String sha256;

    @ManyToMany
    @JoinTable(name = "deployable_component", joinColumns = @JoinColumn(name = "deployable_id"), inverseJoinColumns = @JoinColumn(name = "component_id"))
    private Set<Component> components;




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

    public Set<Component> getComponents() {
        return components;
    }

    public void setComponents(Set<Component> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deployable that = (Deployable) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(environment, that.environment) &&
                Objects.equals(name, that.name) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(buildId, that.buildId) &&
                Objects.equals(sha256, that.sha256);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, environment, name, createDate, buildId, sha256);
    }

    @Override
    public String toString() {
        return "Deployable{" +
                "id=" + id +
                ", environment=" + environment +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", buildId='" + buildId + '\'' +
                ", sha256='" + sha256 + '\'' +
                '}';
    }
}
