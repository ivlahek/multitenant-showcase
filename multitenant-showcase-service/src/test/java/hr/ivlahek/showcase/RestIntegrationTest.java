package hr.ivlahek.showcase;

import hr.ivlahek.showcase.aop.TenantContext;
import hr.ivlahek.showcase.event.MessageReceiver;
import hr.ivlahek.showcase.persistence.IntegrationTest;
import hr.ivlahek.showcase.persistence.entity.Organization;
import hr.ivlahek.showcase.persistence.entity.OrganizationBuilder;
import hr.ivlahek.showcase.persistence.entity.UserAccount;
import hr.ivlahek.showcase.persistence.entity.UserAccountBuilder;
import hr.ivlahek.showcase.persistence.repository.MobileApplicationRepository;
import hr.ivlahek.showcase.persistence.repository.OrganizationRepository;
import hr.ivlahek.showcase.persistence.repository.UserAccountRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Category(IntegrationTest.class)
public abstract class RestIntegrationTest {

    @Autowired
    protected
    OrganizationRepository organizationRepository;
    @Autowired
    protected UserAccountRepository userAccountRepository;
    @Autowired
    protected MobileApplicationRepository mobileApplicationRepository;
    @MockBean
    private MessageReceiver messageReceiver;

    protected Organization organization;
    protected UserAccount userAccount;

    @Before
    public void setUp() {
        organization = OrganizationBuilder.anOrganization().build();
        organizationRepository.save(organization);

        TenantContext.setCurrentTenant(organization);

        userAccount = UserAccountBuilder.anUser().build();
        userAccountRepository.save(userAccount);

        TenantContext.clear();
    }

    @After
    public void tearDown() {
        TenantContext.setCurrentTenant(organization);

        mobileApplicationRepository.deleteAll();
        userAccountRepository.deleteAll();
        organizationRepository.deleteAll();

        TenantContext.clear();
    }
}
