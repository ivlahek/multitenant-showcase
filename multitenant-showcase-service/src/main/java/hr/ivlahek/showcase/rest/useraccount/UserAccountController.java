package hr.ivlahek.showcase.rest.useraccount;

import hr.ivlahek.showcase.aop.InboundRequest;
import hr.ivlahek.showcase.aop.TenantId;
import hr.ivlahek.showcase.dto.user.CreateUserAccountDTO;
import hr.ivlahek.showcase.dto.user.UserAccountDTO;
import hr.ivlahek.showcase.dto.user.UserAccountEndpoints;
import hr.ivlahek.showcase.persistence.entity.UserAccount;
import hr.ivlahek.showcase.persistence.repository.UserAccountRepository;
import hr.ivlahek.showcase.rest.organization.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Transactional
public class UserAccountController {

    @Autowired
    private TenantService tenantService;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserAccountService userAccountService;

    @PostMapping(path = UserAccountEndpoints.USER_RESOURCE)
    @InboundRequest
    public UserAccountDTO create(@PathVariable("organizationId") @TenantId String organizationId,
                                 @RequestBody CreateUserAccountDTO createUserAccountDTO) {
        return map(userAccountService.create(createUserAccountDTO));
    }

    @GetMapping(path = UserAccountEndpoints.USER_RESOURCE_BY_ID)
    @InboundRequest
    public UserAccountDTO getById(@PathVariable("organizationId") @TenantId String organizationId,
                                  @PathVariable("userId") int userAccountId) {
        return map(userAccountService.findById(userAccountId));
    }

    private UserAccountDTO map(UserAccount userAccount) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setOrganizationId(userAccount.getTenant().getExternalId());
        userAccountDTO.setId(userAccount.getId());
        userAccountDTO.setLastName(userAccount.getLastName());
        userAccountDTO.setFirstName(userAccount.getFirstName());
        return userAccountDTO;
    }
}
