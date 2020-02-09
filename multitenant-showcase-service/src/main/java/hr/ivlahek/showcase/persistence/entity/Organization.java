package hr.ivlahek.showcase.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @SequenceGenerator(name = "organization_sequence", sequenceName = "organization_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_sequence")
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "external_id")
    private String externalId;

    @Column(name = "name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
