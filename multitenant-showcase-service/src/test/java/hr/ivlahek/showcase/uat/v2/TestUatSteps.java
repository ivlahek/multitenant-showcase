package hr.ivlahek.showcase.uat.v2;

import hr.ivlahek.showcase.UatAbstractTest;
import hr.ivlahek.showcase.uat.v2.steps.mobileapp.CreateMobileAppEventStep;
import hr.ivlahek.showcase.uat.v2.steps.mobileapp.CreateMobileAppRestStep;
import hr.ivlahek.showcase.uat.v2.steps.notification.SendNotificationRestStep;
import hr.ivlahek.showcase.uat.v2.steps.organization.CreateOrganizationRestStep;
import hr.ivlahek.showcase.uat.v2.steps.user.CreateUserEventStep;
import hr.ivlahek.showcase.uat.v2.steps.user.CreateUserRestStep;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;


public class TestUatSteps extends UatAbstractTest {
    @Autowired
    private CreateOrganizationRestStep createOrganizationRestStep;
    @Autowired
    private CreateMobileAppRestStep createMobileAppStep;
    @Autowired
    private CreateUserRestStep createUserRestStep;
    @Autowired
    private SendNotificationRestStep sendNotificationStep;
    @Autowired
    private CreateUserEventStep createUserEventStep;
    @Autowired
    private CreateMobileAppEventStep createMobileAppEventStep;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() {
        super.setUp();
        createOrganizationRestStep.setRestTemplate(testRestTemplate);
        createMobileAppStep.setRestTemplate(testRestTemplate);
        createMobileAppEventStep.setConsumer(consumer);
        createMobileAppEventStep.setProducer(producer);
        createUserRestStep.setRestTemplate(testRestTemplate);
        createMobileAppEventStep.setRestTemplate(testRestTemplate);
        sendNotificationStep.setTestRestTemplate(testRestTemplate);
        createUserEventStep.setConsumer(consumer);
        createUserEventStep.setProducer(producer);
    }

    @Test
    public void test_1() {
        sendNotificationStep.sendNotification(
                createMobileAppStep.createMobileApp(
                        createUserRestStep.createUserAccount(
                                createOrganizationRestStep.createOrganization()
                        )
                )
        );
    }

    @Test
    public void test_2() throws InterruptedException {
        sendNotificationStep.sendNotification(
                createMobileAppEventStep.createMobileApp(
                        createUserRestStep.createUserAccount(
                                createOrganizationRestStep.createOrganization()
                        )
                )
        );
    }

    @Test
    public void test_3() throws InterruptedException {
        sendNotificationStep.sendNotification(
                createMobileAppStep.createMobileApp(
                        createUserEventStep.createUserAccount(
                                createOrganizationRestStep.createOrganization()
                        )
                )
        );
    }

    @Test
    public void test_4() throws InterruptedException {
        sendNotificationStep.sendNotification(
                createMobileAppEventStep.createMobileApp(
                        createUserEventStep.createUserAccount(
                                createOrganizationRestStep.createOrganization()
                        )
                )
        );
    }
}
