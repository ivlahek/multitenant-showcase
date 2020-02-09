package hr.ivlahek.showcase;

import hr.ivlahek.showcase.aop.TenantContext;
import hr.ivlahek.showcase.event.MessageReceiver;
import hr.ivlahek.showcase.persistence.IntegrationTest;
import hr.ivlahek.showcase.persistence.entity.Tenant;
import hr.ivlahek.showcase.persistence.entity.TenantBuilder;
import hr.ivlahek.showcase.persistence.entity.UserAccount;
import hr.ivlahek.showcase.persistence.entity.UserAccountBuilder;
import hr.ivlahek.showcase.persistence.repository.MobileApplicationRepository;
import hr.ivlahek.showcase.persistence.repository.TenantRepository;
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
    TenantRepository TEnantRepository;
    @Autowired
    protected UserAccountRepository userAccountRepository;
    @Autowired
    protected MobileApplicationRepository mobileApplicationRepository;
    @MockBean
    private MessageReceiver messageReceiver;

    protected Tenant tenant;
    protected UserAccount userAccount;

    @Before
    public void setUp() {
        tenant = TenantBuilder.anOrganization().build();
        TEnantRepository.save(tenant);

        TenantContext.setCurrentTenant(tenant);

        userAccount = UserAccountBuilder.anUser().build();
        userAccountRepository.save(userAccount);

        TenantContext.clear();
    }

    @After
    public void tearDown() {
        TenantContext.setCurrentTenant(tenant);

        mobileApplicationRepository.deleteAll();
        userAccountRepository.deleteAll();
        TEnantRepository.deleteAll();

        TenantContext.clear();
    }
}
