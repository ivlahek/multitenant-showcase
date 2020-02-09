package hr.ivlahek.showcase.rest.organization;

import hr.ivlahek.showcase.RestIntegrationTest;
import hr.ivlahek.showcase.dto.organization.CreateOrganizationDTO;
import hr.ivlahek.showcase.dto.organization.OrganizationDTO;
import hr.ivlahek.showcase.dto.organization.OrganizationEndPoints;
import hr.ivlahek.showcase.persistence.entity.EntityDefaults;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class OrganizationControllerTest extends RestIntegrationTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    public void should_create_organization() {
        CreateOrganizationDTO createOrganization = new CreateOrganizationDTO();
        createOrganization.setName("name");
        createOrganization.setExternalId("lala");

        //OPERATE
        ResponseEntity<OrganizationDTO> responseEntity = restTemplate.postForEntity(OrganizationEndPoints.ORGANIZATION_RESOURCE, createOrganization, OrganizationDTO.class);

        //CHECK
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(organizationRepository.findByExternalId(responseEntity.getBody().getId())).isPresent();
    }

    @Test
    public void should_get_by_id_organization() {
        //OPERATE
        ResponseEntity<OrganizationDTO> responseEntity = restTemplate.getForEntity(OrganizationEndPoints.ORGANIZATION_RESOURCE_ID , OrganizationDTO.class, organization.getExternalId());

        //CHECK
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getId()).isEqualTo(organization.getExternalId());
    }
}