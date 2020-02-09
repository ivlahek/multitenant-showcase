package hr.ivlahek.showcase.rest.notification;

import hr.ivlahek.showcase.aop.InboundRequest;
import hr.ivlahek.showcase.aop.TenantId;
import hr.ivlahek.showcase.dto.notification.NotificationEndPoints;
import hr.ivlahek.showcase.dto.notification.SendNotificationRequest;
import hr.ivlahek.showcase.exception.NotFoundException;
import hr.ivlahek.showcase.exception.messages.ExceptionMessage;
import hr.ivlahek.showcase.persistence.entity.MobileApplication;
import hr.ivlahek.showcase.persistence.repository.MobileApplicationRepository;
import hr.ivlahek.showcase.rest.organization.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping
@RestController
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private MobileApplicationRepository mobileApplicationRepository;

    @PostMapping(path = NotificationEndPoints.SEND_NOTIFICATION)
    @InboundRequest
    public void sendNotification(@PathVariable("organizationId") @TenantId String organizationId,
                                 @RequestBody SendNotificationRequest request) {
        logger.info(request.toString());

        Optional<MobileApplication> optionalMobileApplication = mobileApplicationRepository.findById(request.getMobileApplicationId());

        if (!optionalMobileApplication.isPresent()) {
            logger.error("Mobile application can not be found!");
            throw new NotFoundException(ExceptionMessage.MOBILE_APP_NOT_FOUND);
        }

        send();


    }

    private void send() {

    }
}
