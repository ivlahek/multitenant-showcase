package hr.ivlahek.showcase.persistence.entity;

public final class UserAccountBuilder {
    private Integer id;
    private String firstName = "first-name";
    private String lastName = "last-name";

    private UserAccountBuilder() {
    }

    public static UserAccountBuilder anUser() {
        return new UserAccountBuilder();
    }

    public UserAccountBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public UserAccountBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserAccountBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserAccount build() {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);
        userAccount.setFirstName(firstName);
        userAccount.setLastName(lastName);
        return userAccount;
    }
}
