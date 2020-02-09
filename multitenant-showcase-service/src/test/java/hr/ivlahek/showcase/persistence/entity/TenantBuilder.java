package hr.ivlahek.showcase.persistence.entity;

public final class TenantBuilder {
    private Integer id;
    private String externalId = "100";
    private String name = "default-organization";

    private TenantBuilder() {
    }

    public static TenantBuilder anOrganization() {
        return new TenantBuilder();
    }

    public TenantBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public TenantBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Tenant build() {
        Tenant tenant = new Tenant();
        tenant.setId(id);
        tenant.setExternalId(externalId);
        tenant.setName(name);
        return tenant;
    }
}
