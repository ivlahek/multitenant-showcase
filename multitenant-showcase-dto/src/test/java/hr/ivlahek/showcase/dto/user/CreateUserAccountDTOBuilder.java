package hr.ivlahek.showcase.dto.user;

public final class CreateUserAccountDTOBuilder {
    private String firstName = "first-name";
    private String lastName = "last-name";

    private CreateUserAccountDTOBuilder() {
    }

    public static CreateUserAccountDTOBuilder aCreateUserAccountDTO() {
        return new CreateUserAccountDTOBuilder();
    }

    public CreateUserAccountDTOBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CreateUserAccountDTOBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CreateUserAccountDTO build() {
        CreateUserAccountDTO createUserAccountDTO = new CreateUserAccountDTO();
        createUserAccountDTO.setFirstName(firstName);
        createUserAccountDTO.setLastName(lastName);
        return createUserAccountDTO;
    }
}
