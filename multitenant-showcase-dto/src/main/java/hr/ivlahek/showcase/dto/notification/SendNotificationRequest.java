package hr.ivlahek.showcase.dto.notification;

import lombok.Data;

@Data
public class SendNotificationRequest {

    private String title;

    private String message;

    private int mobileApplicationId;

}
