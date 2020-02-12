package hr.ivlahek.showcase.event;

import hr.ivlahek.showcase.event.dto.Command;
import hr.ivlahek.showcase.event.dto.CreateMobileApplicationCommand;
import hr.ivlahek.showcase.event.dto.CreateUserCommand;
import hr.ivlahek.showcase.mapping.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {

    @Autowired
    private JsonConverter jsonConverter;

    @Autowired
    private MessageHandler messageHandler;

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @KafkaListener(topics = "showcase-topic", groupId = "showcase-consumer")
    public void receiveMessage(String event) {
        logger.info("Event received", event);

        Command command = jsonConverter.readValue(event, Command.class);

        if (command instanceof CreateMobileApplicationCommand) {
            messageHandler.create(command.getOrganizationId(), ((CreateMobileApplicationCommand) command).getCreateMobileApplicationDTO());
        }
        if (command instanceof CreateUserCommand) {
            messageHandler.create(((CreateUserCommand) command).getCreateUserAccountDTO(), command.getOrganizationId());
        }
    }


}
