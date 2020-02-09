package hr.ivlahek.showcase.persistence.repository;

import hr.ivlahek.showcase.aop.TenantContext;
import hr.ivlahek.showcase.event.MessageReceiver;
import hr.ivlahek.showcase.persistence.IntegrationTest;
import hr.ivlahek.showcase.persistence.entity.Organization;
import hr.ivlahek.showcase.persistence.entity.OrganizationBuilder;
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
public abstract class RepositoryTest {

    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private MobileApplicationRepository mobileApplicationRepository;
    @MockBean
    private MessageReceiver messageReceiver;
    Organization organization;

    @Before
    public void setUp() {
        organization = OrganizationBuilder.anOrganization().build();
        organizationRepository.save(organization);

        TenantContext.setCurrentTenant(organization);
    }

    @After
    public void tearDown() throws Exception {
        mobileApplicationRepository.deleteAll();
        userAccountRepository.deleteAll();
        organizationRepository.deleteAll();
        TenantContext.clear();
    }
}
