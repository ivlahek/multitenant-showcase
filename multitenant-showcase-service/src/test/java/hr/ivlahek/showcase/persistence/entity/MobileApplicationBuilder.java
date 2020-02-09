package hr.ivlahek.showcase.persistence.entity;

public final class MobileApplicationBuilder {
    private Integer id;
    private String name = "mobile-app-name";
    private UserAccount userAccount;
    private Tenant tenant;

    private MobileApplicationBuilder() {
    }

    public static MobileApplicationBuilder aMobileApplication() {
        return new MobileApplicationBuilder();
    }

    public MobileApplicationBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public MobileApplicationBuilder withName(String name) {
        this.name = name;
        return this;
    }


    public MobileApplicationBuilder withUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public MobileApplication build() {
        MobileApplication mobileApplication = new MobileApplication();
        mobileApplication.setId(id);

        mobileApplication.setName(name);
        mobileApplication.setUserAccount(userAccount);
        return mobileApplication;
    }

    public MobileApplicationBuilder withOrganization(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }
}
