package hr.ivlahek.showcase.rest.useraccount;

import hr.ivlahek.showcase.dto.user.CreateUserAccountDTO;
import hr.ivlahek.showcase.exception.NotFoundException;
import hr.ivlahek.showcase.exception.messages.ExceptionMessage;
import hr.ivlahek.showcase.persistence.entity.UserAccount;
import hr.ivlahek.showcase.persistence.repository.UserAccountRepository;
import hr.ivlahek.showcase.rest.organization.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private TenantService tenantService;

    public UserAccount findById(int userAccountId) {
        Optional<UserAccount> userAccount = userAccountRepository.getById(userAccountId);

        if (!userAccount.isPresent()) {
            throw new NotFoundException(ExceptionMessage.USER_ACCOUNT_NOT_FOUND);
        }
        return userAccount.get();
    }

    public UserAccount create(CreateUserAccountDTO createUserAccountDTO) {
        UserAccount userAccount = new UserAccount();
        userAccount.setFirstName(createUserAccountDTO.getFirstName());
        userAccount.setLastName(createUserAccountDTO.getLastName());
        userAccountRepository.save(userAccount);
        return userAccount;
    }
}
