package hr.ivlahek.showcase.persistence.repository;

import hr.ivlahek.showcase.persistence.entity.Organization;
import hr.ivlahek.showcase.persistence.entity.OrganizationBuilder;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrganizationRepositoryTest extends RepositoryTest {

    @Test
    public void should_create() {
        Organization organization = OrganizationBuilder.anOrganization().build();

        //OPERATE
        organizationRepository.save(organization);

        //CHECK
        assertThat(organization.getId()).isNotNull();
    }


}