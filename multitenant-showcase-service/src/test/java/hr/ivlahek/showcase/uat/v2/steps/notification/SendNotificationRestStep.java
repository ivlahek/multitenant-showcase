package hr.ivlahek.showcase.uat.v2.steps.notification;

import hr.ivlahek.showcase.dto.mobileapp.MobileApplicationDTO;
import hr.ivlahek.showcase.dto.notification.NotificationEndPoints;
import hr.ivlahek.showcase.dto.notification.SendNotificationRequest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;

@Service
public class SendNotificationRestStep implements SendNotificationStep {
    private TestRestTemplate testRestTemplate;

    @Override
    public void sendNotification(MobileApplicationDTO mobileApplicationDTO) {
        SendNotificationRequest request = new SendNotificationRequest();
        request.setMessage("message");
        int mobileApplicationId = mobileApplicationDTO.getId();
        request.setMobileApplicationId(mobileApplicationId);
        request.setTitle("hello!");

        ResponseEntity<Void> responseEntity = testRestTemplate.postForEntity(NotificationEndPoints.SEND_NOTIFICATION, request, Void.class, mobileApplicationDTO.getOrganizationId());

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    public void setTestRestTemplate(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }
}
