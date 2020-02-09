package hr.ivlahek.showcase.uat.v2.steps.user;

import hr.ivlahek.showcase.dto.organization.TenantDTO;
import hr.ivlahek.showcase.dto.user.CreateUserAccountDTO;
import hr.ivlahek.showcase.dto.user.UserAccountDTO;
import hr.ivlahek.showcase.dto.user.UserAccountEndpoints;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;

@Service
public class CreateUserRestStep implements CreateUserStep {

    protected TestRestTemplate testRestTemplate;

    @Override
    public UserAccountDTO createUserAccount(TenantDTO tenantDTO) {
        CreateUserAccountDTO createUserAccountDTO = new CreateUserAccountDTO();
        createUserAccountDTO.setLastName("last-name");
        createUserAccountDTO.setFirstName("first-name");

        String organizationId = tenantDTO.getId();
        ResponseEntity<UserAccountDTO> accountDTOResponseEntity = testRestTemplate.postForEntity(UserAccountEndpoints.USER_RESOURCE, createUserAccountDTO, UserAccountDTO.class, organizationId);

        assertThat(accountDTOResponseEntity.getStatusCodeValue()).isEqualTo(200);

        return accountDTOResponseEntity.getBody();
    }

    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.testRestTemplate = restTemplate;
    }
}
