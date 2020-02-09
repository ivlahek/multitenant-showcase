package hr.ivlahek.showcase.aop;

import hr.ivlahek.showcase.persistence.entity.Tenant;

public class TenantContext {

    private static ThreadLocal<Tenant> currentTenant = new InheritableThreadLocal<>();

    public static Tenant getCurrentTenant() {
        return currentTenant.get();
    }

    public static void setCurrentTenant(Tenant tenant) {
        currentTenant.set(tenant);
    }

    public static void clear() {
        currentTenant.set(null);
    }

}
