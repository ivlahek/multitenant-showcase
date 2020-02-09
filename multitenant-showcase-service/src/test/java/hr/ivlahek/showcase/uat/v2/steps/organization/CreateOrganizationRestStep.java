package hr.ivlahek.showcase.uat.v2.steps.organization;

import hr.ivlahek.showcase.dto.organization.CreatTenantDTO;
import hr.ivlahek.showcase.dto.organization.TenantDTO;
import hr.ivlahek.showcase.dto.organization.OrganizationEndPoints;
import hr.ivlahek.showcase.persistence.entity.EntityDefaults;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;

@Service
public class CreateOrganizationRestStep implements CreateOrganizationStep {

    protected TestRestTemplate restTemplate;

    @Override
    public TenantDTO createOrganization() {
        CreatTenantDTO createOrganizationResource = new CreatTenantDTO();
        createOrganizationResource.setName("organization-name");
        createOrganizationResource.setExternalId(EntityDefaults.ORGANIZATION_EXTERNAL_ID);

        ResponseEntity<TenantDTO> organizationDTOResponse = restTemplate.postForEntity(OrganizationEndPoints.TENANT_RESOURCE, createOrganizationResource, TenantDTO.class);

        assertThat(organizationDTOResponse.getStatusCodeValue()).isEqualTo(200);

        return organizationDTOResponse.getBody();
    }

    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
