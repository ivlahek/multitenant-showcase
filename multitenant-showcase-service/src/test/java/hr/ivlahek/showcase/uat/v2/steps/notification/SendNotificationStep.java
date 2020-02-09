package hr.ivlahek.showcase.uat.v2.steps.notification;

import hr.ivlahek.showcase.dto.mobileapp.MobileApplicationDTO;

public interface SendNotificationStep {

    public void sendNotification(MobileApplicationDTO mobileApplicationDTO);
}
