package hr.ivlahek.showcase.dto.organization;

import lombok.Data;

@Data
public class CreateOrganizationDTO {

    private String name;

    private String externalId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
