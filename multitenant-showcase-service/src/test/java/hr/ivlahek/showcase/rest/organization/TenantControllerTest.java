package hr.ivlahek.showcase.rest.organization;

import hr.ivlahek.showcase.RestIntegrationTest;
import hr.ivlahek.showcase.dto.organization.CreatTenantDTO;
import hr.ivlahek.showcase.dto.organization.TenantDTO;
import hr.ivlahek.showcase.dto.organization.OrganizationEndPoints;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class TenantControllerTest extends RestIntegrationTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    public void should_create_organization() {
        CreatTenantDTO createOrganization = new CreatTenantDTO();
        createOrganization.setName("name");
        createOrganization.setExternalId("lala");

        //OPERATE
        ResponseEntity<TenantDTO> responseEntity = restTemplate.postForEntity(OrganizationEndPoints.TENANT_RESOURCE, createOrganization, TenantDTO.class);

        //CHECK
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(TEnantRepository.findByExternalId(responseEntity.getBody().getId())).isPresent();
    }

    @Test
    public void should_get_by_id_organization() {
        //OPERATE
        ResponseEntity<TenantDTO> responseEntity = restTemplate.getForEntity(OrganizationEndPoints.TENANT_RESOURCE_ID, TenantDTO.class, tenant.getExternalId());

        //CHECK
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getId()).isEqualTo(tenant.getExternalId());
    }
}