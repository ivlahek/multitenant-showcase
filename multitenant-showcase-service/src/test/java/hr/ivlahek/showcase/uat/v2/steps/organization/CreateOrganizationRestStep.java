package hr.ivlahek.showcase.uat.v2.steps.organization;

import hr.ivlahek.showcase.dto.organization.CreateOrganizationDTO;
import hr.ivlahek.showcase.dto.organization.OrganizationDTO;
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
    public OrganizationDTO createOrganization() {
        CreateOrganizationDTO createOrganizationResource = new CreateOrganizationDTO();
        createOrganizationResource.setName("organization-name");
        createOrganizationResource.setExternalId(EntityDefaults.ORGANIZATION_EXTERNAL_ID);

        ResponseEntity<OrganizationDTO> organizationDTOResponse = restTemplate.postForEntity(OrganizationEndPoints.ORGANIZATION_RESOURCE, createOrganizationResource, OrganizationDTO.class);

        assertThat(organizationDTOResponse.getStatusCodeValue()).isEqualTo(200);

        return organizationDTOResponse.getBody();
    }

    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
