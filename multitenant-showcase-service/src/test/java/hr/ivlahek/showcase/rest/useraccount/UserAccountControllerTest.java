package hr.ivlahek.showcase.rest.useraccount;

import hr.ivlahek.showcase.RestIntegrationTest;
import hr.ivlahek.showcase.dto.user.CreateUserAccountDTO;
import hr.ivlahek.showcase.dto.user.UserAccountDTO;
import hr.ivlahek.showcase.dto.user.UserAccountEndpoints;
import hr.ivlahek.showcase.persistence.asserter.UserAccountAsserter;
import hr.ivlahek.showcase.persistence.entity.UserAccount;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAccountControllerTest extends RestIntegrationTest {
    @Autowired
    protected TestRestTemplate restTemplate;


    @Test
    public void should_create_user_account() {
        CreateUserAccountDTO createUserAccountDTO = new CreateUserAccountDTO();
        createUserAccountDTO.setFirstName("first-name");
        createUserAccountDTO.setLastName("last-name");

        //OPERATE
        ResponseEntity<UserAccountDTO> responseEntity = restTemplate.postForEntity(UserAccountEndpoints.USER_RESOURCE, createUserAccountDTO, UserAccountDTO.class, tenant.getExternalId());

        //CHECK
        assertThat(responseEntity.getBody().getId()).isNotNull();
        UserAccount userAccount = userAccountRepository.findById(responseEntity.getBody().getId()).get();
        assertThat(userAccount).isNotNull();
        new UserAccountAsserter()
                .withOrganizationId(tenant.getId())
                .assertUserAccount(userAccount, createUserAccountDTO);

    }

    @Test
    public void should_get_user_account() {
        //OPERATE
        ResponseEntity<UserAccountDTO> responseEntity = restTemplate.getForEntity(UserAccountEndpoints.USER_RESOURCE_BY_ID, UserAccountDTO.class, tenant.getExternalId(), userAccount.getId());

        //CHECK
        assertThat(responseEntity.getBody().getId()).isEqualTo(userAccount.getId());
        new UserAccountAsserter()
                .withOrganizationId(tenant.getId())
                .assertUserAccount(responseEntity.getBody(), userAccount);
    }
}