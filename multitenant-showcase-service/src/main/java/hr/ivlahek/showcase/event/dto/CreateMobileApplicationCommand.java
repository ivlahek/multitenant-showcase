package hr.ivlahek.showcase.event.dto;

import hr.ivlahek.showcase.dto.mobileapp.CreateMobileApplicationDTO;

public class CreateMobileApplicationCommand extends Command {
    private CreateMobileApplicationDTO createMobileApplicationDTO;

    public CreateMobileApplicationCommand() {
    }

    public CreateMobileApplicationCommand(CreateMobileApplicationDTO createMobileApplicationDTO, String organizationId) {
        super(organizationId);
        this.createMobileApplicationDTO = createMobileApplicationDTO;
    }

    public CreateMobileApplicationDTO getCreateMobileApplicationDTO() {
        return createMobileApplicationDTO;
    }

    public void setCreateMobileApplicationDTO(CreateMobileApplicationDTO createMobileApplicationDTO) {
        this.createMobileApplicationDTO = createMobileApplicationDTO;
    }

    @Override
    public String toString() {
        return "CreateMobileApplicationCommand{" +
                "createMobileApplicationDTO=" + createMobileApplicationDTO +
                '}';
    }
}
