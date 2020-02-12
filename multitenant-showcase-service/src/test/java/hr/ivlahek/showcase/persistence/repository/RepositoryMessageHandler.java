package hr.ivlahek.showcase.persistence.repository;

import hr.ivlahek.showcase.aop.TenantContext;
import hr.ivlahek.showcase.event.MessageReceiver;
import hr.ivlahek.showcase.persistence.IntegrationTest;
import hr.ivlahek.showcase.persistence.entity.Tenant;
import hr.ivlahek.showcase.persistence.entity.TenantBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestJpaConfig.class})
public abstract class RepositoryMessageHandler {

    @Autowired
    TenantRepository tenantRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private MobileApplicationRepository mobileApplicationRepository;
    @MockBean
    private MessageReceiver messageReceiver;
    Tenant tenant;

    @Before
    public void setUp() {
        tenant = TenantBuilder.anOrganization().build();
        tenantRepository.save(tenant);

        TenantContext.setCurrentTenant(tenant);
    }

    @After
    public void tearDown() throws Exception {
        mobileApplicationRepository.deleteAll();
        userAccountRepository.deleteAll();
        tenantRepository.deleteAll();
        TenantContext.clear();
    }
}
