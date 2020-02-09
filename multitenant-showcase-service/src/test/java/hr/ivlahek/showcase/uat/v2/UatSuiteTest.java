package hr.ivlahek.showcase.uat.v2;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestUatSteps.class})
public class UatSuiteTest {
//
//    public static KafkaMessageListenerContainer<String, String> container;
//    public static BlockingQueue<ConsumerRecord<String, String>> records;
//
//    @ClassRule
//    public static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, false, "showcase-topic");
//
//    @BeforeClass
//    public static void beforeClass() {
//        Map<String, Object> consumerProperties =
//                KafkaTestUtils.consumerProps("test-consumer", "false", embeddedKafka.getEmbeddedKafka());
//
//        // create a Kafka consumer factory
//        DefaultKafkaConsumerFactory<String, String> consumerFactory =
//                new DefaultKafkaConsumerFactory<String, String>(consumerProperties);
//
//        // set the topic that needs to be consumed
//        ContainerProperties containerProperties = new ContainerProperties("showcase-topic");
//
//        // create a Kafka MessageListenerContainer
//        container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
//
//        // create a thread safe queue to store the received message
//        records = new LinkedBlockingQueue<>();
//
//        // setup a Kafka message listener
//        container.setupMessageListener(new MessageListener<String, String>() {
//            @Override
//            public void onMessage(ConsumerRecord<String, String> record) {
//                records.add(record);
//            }
//        });
//
//        // start the container and underlying message listener
//        container.start();
//
//        // wait until the container has the required number of assigned partitions
//        ContainerTestUtils.waitForAssignment(container, embeddedKafka.getEmbeddedKafka().getPartitionsPerTopic());
//    }
}
