package hr.ivlahek.showcase.uat.v2.steps.mobileapp;

import hr.ivlahek.showcase.dto.mobileapp.CreateMobileApplicationDTO;
import hr.ivlahek.showcase.dto.mobileapp.CreateMobileApplicationDTOBuilder;
import hr.ivlahek.showcase.dto.mobileapp.MobileApplicationDTO;
import hr.ivlahek.showcase.dto.user.UserAccountDTO;
import hr.ivlahek.showcase.event.dto.CreateMobileApplicationCommand;
import hr.ivlahek.showcase.event.dto.CreateMobileApplicationCommandBuilder;
import hr.ivlahek.showcase.mapping.JsonConverter;
import hr.ivlahek.showcase.persistence.entity.EntityDefaults;
import hr.ivlahek.showcase.persistence.entity.MobileApplication;
import hr.ivlahek.showcase.persistence.repository.MobileApplicationRepository;
import hr.ivlahek.showcase.persistence.repository.UserAccountRepository;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateMobileAppEventStep implements CreateMobileAppStep {

    @Autowired
    protected UserAccountRepository userAccountRepository;
    @Autowired
    protected MobileApplicationRepository mobileApplicationRepository;
    @Autowired
    private JsonConverter jsonConverter;

    protected TestRestTemplate restTemplate;
    private KafkaProducer producer;
    private Consumer<String, String> consumer;


    @Override
    public MobileApplicationDTO createMobileApp(UserAccountDTO userAccountDTO) throws InterruptedException {
        CreateMobileApplicationDTO createMobileApplicationDTO = CreateMobileApplicationDTOBuilder.aCreateMobileApplicationDTO().withUserAccountId(userAccountDTO.getId()).build();
        CreateMobileApplicationCommand createMobileApplicationCommand = CreateMobileApplicationCommandBuilder.aCreateMobileApplicationCommand()
                .withCreateMobileApplicationDTO(createMobileApplicationDTO)
                .withOrganizationId(userAccountDTO.getOrganizationId()).build();

        producer.send(new ProducerRecord<>("showcase-topic", 0, null, jsonConverter.write(createMobileApplicationCommand)));

        Thread.sleep(2000);
        List<MobileApplication> mobileApplications = mobileApplicationRepository.findByName(createMobileApplicationDTO.getName());

        MobileApplication mobileApplication = mobileApplications.get(0);
        MobileApplicationDTO mobileApplicationDTO = new MobileApplicationDTO();
        mobileApplicationDTO.setId(mobileApplication.getId());
        mobileApplicationDTO.setOrganizationId(EntityDefaults.ORGANIZATION_EXTERNAL_ID);
        return mobileApplicationDTO;
    }

    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setProducer(KafkaProducer producer) {
        this.producer = producer;
    }

    public void setConsumer(Consumer<String, String> consumer) {
        this.consumer = consumer;
    }
}
