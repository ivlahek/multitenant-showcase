package hr.ivlahek.showcase.aop;

import hr.ivlahek.showcase.persistence.entity.Organization;

public class TenantContext {

    private static ThreadLocal<Organization> currentTenant = new InheritableThreadLocal<>();

    public static Organization getCurrentOrganization() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(Organization tenant) {
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.set(null);
    }

}
