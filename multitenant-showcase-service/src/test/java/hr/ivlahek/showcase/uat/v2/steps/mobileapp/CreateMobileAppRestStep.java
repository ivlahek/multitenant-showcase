package hr.ivlahek.showcase.uat.v2.steps.mobileapp;

import hr.ivlahek.showcase.dto.mobileapp.CreateMobileApplicationDTO;
import hr.ivlahek.showcase.dto.mobileapp.MobileApplicationDTO;
import hr.ivlahek.showcase.dto.mobileapp.MobileApplicationEndPoints;
import hr.ivlahek.showcase.dto.user.UserAccountDTO;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;

@Service
public class CreateMobileAppRestStep implements CreateMobileAppStep {

    protected TestRestTemplate restTemplate;

    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MobileApplicationDTO createMobileApp(UserAccountDTO userAccountDTO) {
        CreateMobileApplicationDTO createMobileApplicationDTO = new CreateMobileApplicationDTO();
        Integer userAccountId = userAccountDTO.getId();
        createMobileApplicationDTO.setUserAccountId(userAccountId);
        createMobileApplicationDTO.setName("name");

        ResponseEntity<MobileApplicationDTO> mobileApplicationDTOResponseEntity = restTemplate.postForEntity(MobileApplicationEndPoints.MOBILE_APPLICATION_RESOURCE, createMobileApplicationDTO, MobileApplicationDTO.class, userAccountDTO.getOrganizationId());

        assertThat(mobileApplicationDTOResponseEntity.getStatusCodeValue()).isEqualTo(200);
        return mobileApplicationDTOResponseEntity.getBody();
    }
}
