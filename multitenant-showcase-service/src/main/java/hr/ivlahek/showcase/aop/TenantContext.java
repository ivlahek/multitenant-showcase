package hr.ivlahek.showcase.aop;

import hr.ivlahek.showcase.persistence.entity.Tenant;

public class TenantContext {

    private static ThreadLocal<Tenant> currentTenant = new InheritableThreadLocal<>();
    private static ThreadLocal<Integer> currentTenantId = new InheritableThreadLocal<>();

    public static Tenant getCurrentTenant() {
        return currentTenant.get();
    }

    public static Integer getCurrentTenantId() {
        return currentTenantId.get();
    }

    public static void setCurrentTenant(Tenant tenant) {
        currentTenant.set(tenant);
        currentTenantId.set(tenant.getId());
    }

    public static void clear() {
        currentTenant.set(null);
    }

}
