package hr.ivlahek.showcase.persistence.asserter;

import hr.ivlahek.showcase.dto.user.CreateUserAccountDTO;
import hr.ivlahek.showcase.dto.user.UserAccountDTO;
import hr.ivlahek.showcase.persistence.entity.UserAccount;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAccountAsserter {

    private int organizationId;

    public void assertUserAccount(UserAccount userAccount, CreateUserAccountDTO createUserAccountDTO) {
        assertThat(userAccount.getFirstName()).isEqualTo(createUserAccountDTO.getFirstName());
        assertThat(userAccount.getLastName()).isEqualTo(createUserAccountDTO.getLastName());
        assertThat(userAccount.getOrganization().getId()).isEqualTo(organizationId);
    }

    public void assertUserAccount(UserAccountDTO userAccountDTO, UserAccount userAccount) {
        assertThat(userAccount.getFirstName()).isEqualTo(userAccountDTO.getFirstName());
        assertThat(userAccount.getLastName()).isEqualTo(userAccountDTO.getLastName());
        assertThat(userAccount.getOrganization().getId()).isEqualTo(organizationId);
    }

    public UserAccountAsserter withOrganizationId(int organizationId) {
        this.organizationId = organizationId;
        return this;
    }
}
