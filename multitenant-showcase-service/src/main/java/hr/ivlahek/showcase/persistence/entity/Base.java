package hr.ivlahek.showcase.persistence.entity;

import hr.ivlahek.showcase.aop.TenantContext;

import javax.persistence.*;

@MappedSuperclass
public class Base {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;


    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @PrePersist
    @PreUpdate
    public void prePersist() {
        setTenant(TenantContext.getCurrentTenant());
    }
}
