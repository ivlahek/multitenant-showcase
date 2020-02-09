package hr.ivlahek.showcase.uat.v1;

import hr.ivlahek.showcase.UatAbstractTest;
import hr.ivlahek.showcase.dto.mobileapp.CreateMobileApplicationDTO;
import hr.ivlahek.showcase.dto.mobileapp.CreateMobileApplicationDTOBuilder;
import hr.ivlahek.showcase.dto.notification.NotificationEndPoints;
import hr.ivlahek.showcase.dto.notification.SendNotificationRequest;
import hr.ivlahek.showcase.dto.organization.CreatTenantDTO;
import hr.ivlahek.showcase.dto.organization.TenantDTO;
import hr.ivlahek.showcase.dto.organization.OrganizationEndPoints;
import hr.ivlahek.showcase.dto.user.CreateUserAccountDTO;
import hr.ivlahek.showcase.dto.user.UserAccountDTO;
import hr.ivlahek.showcase.dto.user.UserAccountEndpoints;
import hr.ivlahek.showcase.event.dto.CreateMobileApplicationCommand;
import hr.ivlahek.showcase.event.dto.CreateMobileApplicationCommandBuilder;
import hr.ivlahek.showcase.persistence.entity.EntityDefaults;
import hr.ivlahek.showcase.persistence.entity.MobileApplication;
import hr.ivlahek.showcase.persistence.repository.UserAccountRepository;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UatEventTest extends UatAbstractTest {


    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Test
    @Ignore
    public void should_create_organization_rest__user_with_rest__mobile_app_with_event__and_send_notification() throws InterruptedException {
        CreatTenantDTO createOrganizationResource = new CreatTenantDTO();
        createOrganizationResource.setName("organization-name");
        createOrganizationResource.setExternalId(EntityDefaults.ORGANIZATION_EXTERNAL_ID);

        ResponseEntity<TenantDTO> organizationDTOResponse = testRestTemplate.postForEntity(OrganizationEndPoints.TENANT_RESOURCE, createOrganizationResource, TenantDTO.class);

        assertThat(organizationDTOResponse.getStatusCodeValue()).isEqualTo(200);

        CreateUserAccountDTO createUserAccountDTO = new CreateUserAccountDTO();
        createUserAccountDTO.setLastName("last-name");
        createUserAccountDTO.setFirstName("first-name");

        String organizationId = organizationDTOResponse.getBody().getId();
        ResponseEntity<UserAccountDTO> accountDTOResponseEntity = testRestTemplate.postForEntity(UserAccountEndpoints.USER_RESOURCE, createUserAccountDTO, UserAccountDTO.class, organizationId);

        assertThat(accountDTOResponseEntity.getStatusCodeValue()).isEqualTo(200);

        CreateMobileApplicationDTO createMobileApplicationDTO = CreateMobileApplicationDTOBuilder.aCreateMobileApplicationDTO().withUserAccountId(accountDTOResponseEntity.getBody().getId()).build();
        CreateMobileApplicationCommand createMobileApplicationCommand = CreateMobileApplicationCommandBuilder.aCreateMobileApplicationCommand()
                .withCreateMobileApplicationDTO(createMobileApplicationDTO)
                .withOrganizationId(organizationId).build();
        producer.send(new ProducerRecord<>("showcase-topic", 0, null, jsonConverter.write(createMobileApplicationCommand)));

        Thread.sleep(2000);
//        ConsumerRecord<String, String> received = KafkaTestUtils.getSingleRecord(consumer, "showcase-topic");
        List<MobileApplication> mobileApplications = mobileApplicationRepository.findByName(createMobileApplicationDTO.getName());


        SendNotificationRequest request = new SendNotificationRequest();
        request.setMessage("message");
        int mobileApplicationId = mobileApplications.get(0).getId();
        request.setMobileApplicationId(mobileApplicationId);
        request.setTitle("hello!");

        ResponseEntity<Void> responseEntity = testRestTemplate.postForEntity(NotificationEndPoints.SEND_NOTIFICATION, request, Void.class, organizationId);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
}
