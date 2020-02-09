package hr.ivlahek.showcase.event.dto;

import hr.ivlahek.showcase.dto.mobileapp.CreateMobileApplicationDTO;
import hr.ivlahek.showcase.mapping.JsonConverter;
import org.junit.Before;
import org.junit.Test;

public class CommandTest {

    private JsonConverter jsonConverter;

    @Before
    public void setUp() throws Exception {
        jsonConverter = new JsonConverter();
        jsonConverter.init();
    }

    @Test
    public void should_serialize_deserialize() {
        CreateMobileApplicationDTO createMobileApp = new CreateMobileApplicationDTO();
        createMobileApp.setName("name");
        createMobileApp.setUserAccountId(1);

        String json = jsonConverter.write(new CreateMobileApplicationCommand(createMobileApp, "100"));

        CreateMobileApplicationCommand object = (CreateMobileApplicationCommand) jsonConverter.readValue(json, Command.class);
    }

    @Test
    public void should_deserialize() {
        CreateUserCommand createUserCommand = CreateUserCommandBuilder.aCreateUserCommand().build();
        String json = jsonConverter.write(createUserCommand);
        CreateUserCommand object = (CreateUserCommand) jsonConverter.readValue(json, Command.class);
    }
}