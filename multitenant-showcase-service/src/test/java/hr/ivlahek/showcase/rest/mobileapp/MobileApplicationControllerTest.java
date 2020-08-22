package hr.ivlahek.showcase.rest.mobileapp;

import hr.ivlahek.showcase.RestIntegrationTest;
import hr.ivlahek.showcase.aop.TenantContext;
import hr.ivlahek.showcase.dto.mobileapp.CreateMobileApplicationDTO;
import hr.ivlahek.showcase.dto.mobileapp.MobileApplicationDTO;
import hr.ivlahek.showcase.dto.mobileapp.MobileApplicationEndPoints;
import hr.ivlahek.showcase.persistence.entity.MobileApplication;
import hr.ivlahek.showcase.persistence.entity.MobileApplicationBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class MobileApplicationControllerTest extends RestIntegrationTest {
    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    public void should_create_mobile_app() {
        CreateMobileApplicationDTO createMobileApplicationDTO = new CreateMobileApplicationDTO();
        createMobileApplicationDTO.setName("name");
        createMobileApplicationDTO.setUserAccountId(userAccount.getId());

        //OPERATE
        ResponseEntity<MobileApplicationDTO> responseEntity = restTemplate.postForEntity(MobileApplicationEndPoints.MOBILE_APPLICATION_RESOURCE, createMobileApplicationDTO, MobileApplicationDTO.class, tenant.getExternalId());

        //CHECK
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(mobileApplicationRepository.getById(responseEntity.getBody().getId())).isPresent();
    }

    @Test
    public void should_get_by_id_mobile_app_id() {
        TenantContext.setCurrentTenant(tenant);
        MobileApplication mobileApplication = MobileApplicationBuilder.aMobileApplication().withUserAccount(userAccount).build();
        mobileApplicationRepository.save(mobileApplication);
        TenantContext.clear();

        //OPERATE
        ResponseEntity<MobileApplicationDTO> responseEntity = restTemplate.getForEntity(MobileApplicationEndPoints.MOBILE_APPLICATION_RESOURCE_BY_ID, MobileApplicationDTO.class, tenant.getExternalId(), mobileApplication.getId());

        //CHECK
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getBody().getId()).isEqualTo(mobileApplication.getId());
    }
}