package hr.ivlahek.showcase.dto.mobileapp;

public final class CreateMobileApplicationDTOBuilder {
    private String name = "name";
    private Integer userAccountId;

    private CreateMobileApplicationDTOBuilder() {
    }

    public static CreateMobileApplicationDTOBuilder aCreateMobileApplicationDTO() {
        return new CreateMobileApplicationDTOBuilder();
    }

    public CreateMobileApplicationDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CreateMobileApplicationDTOBuilder withUserAccountId(Integer userAccountId) {
        this.userAccountId = userAccountId;
        return this;
    }

    public CreateMobileApplicationDTO build() {
        CreateMobileApplicationDTO createMobileApplicationDTO = new CreateMobileApplicationDTO();
        createMobileApplicationDTO.setName(name);
        createMobileApplicationDTO.setUserAccountId(userAccountId);
        return createMobileApplicationDTO;
    }
}
