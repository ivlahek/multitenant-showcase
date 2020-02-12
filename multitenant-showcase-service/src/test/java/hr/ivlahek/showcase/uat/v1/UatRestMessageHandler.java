package hr.ivlahek.showcase.uat.v1;

import hr.ivlahek.showcase.UatAbstractMessageHandler;
import hr.ivlahek.showcase.dto.mobileapp.CreateMobileApplicationDTO;
import hr.ivlahek.showcase.dto.mobileapp.MobileApplicationDTO;
import hr.ivlahek.showcase.dto.mobileapp.MobileApplicationEndPoints;
import hr.ivlahek.showcase.dto.notification.NotificationEndPoints;
import hr.ivlahek.showcase.dto.notification.SendNotificationRequest;
import hr.ivlahek.showcase.dto.organization.CreatTenantDTO;
import hr.ivlahek.showcase.dto.organization.TenantDTO;
import hr.ivlahek.showcase.dto.organization.OrganizationEndPoints;
import hr.ivlahek.showcase.dto.user.CreateUserAccountDTO;
import hr.ivlahek.showcase.dto.user.UserAccountDTO;
import hr.ivlahek.showcase.dto.user.UserAccountEndpoints;
import hr.ivlahek.showcase.persistence.entity.EntityDefaults;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class UatRestMessageHandler extends UatAbstractMessageHandler {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void should_send_notification_users_created_using_rest_methods() {
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


        CreateMobileApplicationDTO createMobileApplicationDTO = new CreateMobileApplicationDTO();
        Integer userAccountId = accountDTOResponseEntity.getBody().getId();
        createMobileApplicationDTO.setUserAccountId(userAccountId);
        createMobileApplicationDTO.setName("name");

        ResponseEntity<MobileApplicationDTO> mobileApplicationDTOResponseEntity = testRestTemplate.postForEntity(MobileApplicationEndPoints.MOBILE_APPLICATION_RESOURCE, createMobileApplicationDTO, MobileApplicationDTO.class, organizationId);

        assertThat(mobileApplicationDTOResponseEntity.getStatusCodeValue()).isEqualTo(200);


        SendNotificationRequest request = new SendNotificationRequest();
        request.setMessage("message");
        int mobileApplicationId = mobileApplicationDTOResponseEntity.getBody().getId();
        request.setMobileApplicationId(mobileApplicationId);
        request.setTitle("hello!");

        ResponseEntity<Void> responseEntity = testRestTemplate.postForEntity(NotificationEndPoints.SEND_NOTIFICATION, request, Void.class, organizationId);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

    }

}
