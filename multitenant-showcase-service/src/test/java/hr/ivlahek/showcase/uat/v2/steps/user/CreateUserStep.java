package hr.ivlahek.showcase.uat.v2.steps.user;

import hr.ivlahek.showcase.dto.organization.OrganizationDTO;
import hr.ivlahek.showcase.dto.user.UserAccountDTO;

public interface CreateUserStep {
    UserAccountDTO createUserAccount(OrganizationDTO organizationDTO) throws InterruptedException;
}
