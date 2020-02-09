package hr.ivlahek.showcase.event.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "commandType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateUserCommand.class),
        @JsonSubTypes.Type(value = CreateMobileApplicationCommand.class)
})
public abstract class Command {

    private String organizationId;

    public Command() {
    }

    public Command(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public String toString() {
        return "Command{" +
                "organizationId=" + organizationId +
                '}';
    }
}
