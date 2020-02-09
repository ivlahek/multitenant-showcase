package hr.ivlahek.showcase.event.dto;

import hr.ivlahek.showcase.dto.user.CreateUserAccountDTO;

public class CreateUserCommand extends Command {
    private CreateUserAccountDTO createUserAccountDTO;

    public CreateUserCommand(String organizationId, CreateUserAccountDTO createUserAccountDTO) {
        super(organizationId);
        this.createUserAccountDTO = createUserAccountDTO;
    }

    public CreateUserCommand() {
    }

    public CreateUserAccountDTO getCreateUserAccountDTO() {
        return createUserAccountDTO;
    }

    public void setCreateUserAccountDTO(CreateUserAccountDTO createUserAccountDTO) {
        this.createUserAccountDTO = createUserAccountDTO;
    }

    @Override
    public String toString() {
        return "CreateUserCommand{" +
                "createUserAccountDTO=" + createUserAccountDTO +
                '}';
    }
}
