package hr.ivlahek.showcase;

import hr.ivlahek.showcase.mapping.JsonConverter;
import hr.ivlahek.showcase.persistence.IntegrationTest;
import hr.ivlahek.showcase.persistence.repository.MobileApplicationRepository;
import hr.ivlahek.showcase.persistence.repository.OrganizationRepository;
import hr.ivlahek.showcase.persistence.repository.UserAccountRepository;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@ActiveProfiles(profiles = "kafka-test")
@SpringBootTest(classes = {ShowcaseApp.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@Category(IntegrationTest.class)
@EmbeddedKafka(topics = {"showcase-topic"},
        controlledShutdown = true,
        brokerProperties = {"delete.topic.enable=true"},
        count = 1,
        partitions = 1)

public abstract class UatAbstractTest {
    @Autowired
    protected
    OrganizationRepository organizationRepository;
    @Autowired
    protected UserAccountRepository userAccountRepository;
    @Autowired
    protected MobileApplicationRepository mobileApplicationRepository;
    @Autowired
    private KafkaProperties kafkaProperties;
    @Autowired
    protected JsonConverter jsonConverter;
    protected KafkaProducer<String, String> producer;
    protected KafkaConsumer<String, String> consumer;

    @Before
    public void setUp() {
        Map<String, Object> senderProps = kafkaProperties.buildProducerProperties();

        producer = new KafkaProducer<>(senderProps);
        consumer = new KafkaConsumer<>(kafkaProperties.buildConsumerProperties());
        consumer.subscribe(Lists.newArrayList("showcase-topic"));
    }


    @After
    public void tearDown() {
        mobileApplicationRepository.deleteAll();
        userAccountRepository.deleteAll();
        organizationRepository.deleteAll();
    }

}
