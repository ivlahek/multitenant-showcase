package hr.ivlahek.showcase.persistence.entity;

import hr.ivlahek.showcase.aop.TenantContext;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;

@Entity
@Table(name = "mobile_application")
@FilterDef(name = "mobileApplicationFilter", parameters = {@ParamDef(name = "organizationId", type = "int")})
@Filter(name = "mobileApplicationFilter", condition = "organization_id = :organizationId")
public class MobileApplication {

    @Id
    @SequenceGenerator(name = "mobile_application_sequence", sequenceName = "mobile_application_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mobile_application_sequence")
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

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

    public Tenant getTenant() {
        return tenant;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @PrePersist
    @PreUpdate
    public void prePersist() {
        tenant = TenantContext.getCurrentTenant();
    }
}
