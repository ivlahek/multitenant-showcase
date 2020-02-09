package hr.ivlahek.showcase.event.dto;

import hr.ivlahek.showcase.dto.user.CreateUserAccountDTO;
import hr.ivlahek.showcase.dto.user.CreateUserAccountDTOBuilder;
import hr.ivlahek.showcase.persistence.entity.EntityDefaults;

public final class CreateUserCommandBuilder {
    private CreateUserAccountDTO createUserAccountDTO = CreateUserAccountDTOBuilder.aCreateUserAccountDTO().build();
    private String organizationId = EntityDefaults.ORGANIZATION_EXTERNAL_ID;

    private CreateUserCommandBuilder() {
    }

    public static CreateUserCommandBuilder aCreateUserCommand() {
        return new CreateUserCommandBuilder();
    }

    public CreateUserCommandBuilder withCreateUserAccountDTO(CreateUserAccountDTO createUserAccountDTO) {
        this.createUserAccountDTO = createUserAccountDTO;
        return this;
    }

    public CreateUserCommandBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public CreateUserCommand build() {
        return new CreateUserCommand(organizationId, createUserAccountDTO);
    }
}
