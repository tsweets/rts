package org.beer30.rts.domain;

import org.beer30.rts.domain.enums.ComponentType;
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
    private Instant createDate;

    @Column
    private String name;

    @Column
    private ComponentType componentType;

    @Column
    private String componentReference;

    @Column
    private String scmHash;


    @Column
    private String buildId;


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


    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public String getComponentReference() {
        return componentReference;
    }

    public void setComponentReference(String componentReference) {
        this.componentReference = componentReference;
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
                Objects.equals(buildId, component.buildId) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, environment, name, scmHash, createDate, buildId);
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
                ", componentType=" + componentType +
                ", componentReference='" + componentReference + '\'' +
                '}';
    }
}
