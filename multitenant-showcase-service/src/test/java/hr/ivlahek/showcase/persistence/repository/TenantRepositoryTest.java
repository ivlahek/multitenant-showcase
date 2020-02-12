package hr.ivlahek.showcase.persistence.repository;

import hr.ivlahek.showcase.persistence.entity.Tenant;
import hr.ivlahek.showcase.persistence.entity.TenantBuilder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TenantRepositoryTest extends RepositoryMessageHandler {

    @Test
    public void should_create() {
        Tenant tenant = TenantBuilder.anOrganization().build();

        //OPERATE
        tenantRepository.save(tenant);

        //CHECK
        assertThat(tenant.getId()).isNotNull();
    }


}