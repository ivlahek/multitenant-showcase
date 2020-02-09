package hr.ivlahek.showcase.uat.v2.steps.user;

import hr.ivlahek.showcase.dto.organization.OrganizationDTO;
import hr.ivlahek.showcase.dto.user.CreateUserAccountDTO;
import hr.ivlahek.showcase.dto.user.CreateUserAccountDTOBuilder;
import hr.ivlahek.showcase.dto.user.UserAccountDTO;
import hr.ivlahek.showcase.event.dto.CreateUserCommand;
import hr.ivlahek.showcase.event.dto.CreateUserCommandBuilder;
import hr.ivlahek.showcase.mapping.JsonConverter;
import hr.ivlahek.showcase.persistence.entity.EntityDefaults;
import hr.ivlahek.showcase.persistence.entity.UserAccount;
import hr.ivlahek.showcase.persistence.repository.UserAccountRepository;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateUserEventStep implements CreateUserStep {

    @Autowired
    private JsonConverter jsonConverter;
    @Autowired
    private UserAccountRepository userAccountRepository;
    private Consumer consumer;
    private Producer producer;

    @Override
    public UserAccountDTO createUserAccount(OrganizationDTO organizationDTO) throws InterruptedException {
        CreateUserAccountDTO createUserAccountDTO = CreateUserAccountDTOBuilder.aCreateUserAccountDTO().build();
        CreateUserCommand createUserCommand = CreateUserCommandBuilder.aCreateUserCommand()
                .withOrganizationId(organizationDTO.getId())
                .withCreateUserAccountDTO(createUserAccountDTO)
                .build();
        producer.send(new ProducerRecord<>("showcase-topic", 0, null, jsonConverter.write(createUserCommand)));

        Thread.sleep(500);

        List<UserAccount> userAccountList = userAccountRepository.findByFirstName(createUserAccountDTO.getFirstName());

        UserAccountDTO userAccountDTO = new UserAccountDTO();
        UserAccount userAccount = userAccountList.get(0);
        userAccountDTO.setOrganizationId(EntityDefaults.ORGANIZATION_EXTERNAL_ID);
        userAccountDTO.setId(userAccount.getId());
        return userAccountDTO;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
