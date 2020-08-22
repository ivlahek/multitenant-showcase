package hr.ivlahek.showcase.rest.mobileapp;

import hr.ivlahek.showcase.dto.mobileapp.CreateMobileApplicationDTO;
import hr.ivlahek.showcase.exception.NotFoundException;
import hr.ivlahek.showcase.exception.messages.ExceptionMessage;
import hr.ivlahek.showcase.persistence.entity.MobileApplication;
import hr.ivlahek.showcase.persistence.entity.UserAccount;
import hr.ivlahek.showcase.persistence.repository.MobileApplicationRepository;
import hr.ivlahek.showcase.rest.organization.TenantService;
import hr.ivlahek.showcase.rest.useraccount.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MobileApplicationService {
    @Autowired
    private MobileApplicationRepository mobileApplicationRepository;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private UserAccountService userAccountService;

    public MobileApplication findById(int mobileAppId) {
        Optional<MobileApplication> optionalMobileApplication = mobileApplicationRepository.getById(mobileAppId);

        if (!optionalMobileApplication.isPresent()) {
            throw new NotFoundException(ExceptionMessage.MOBILE_APP_NOT_FOUND);
        }
        return optionalMobileApplication.get();
    }

    public MobileApplication create(CreateMobileApplicationDTO createMobileApplicationDTO) {
        UserAccount userAccount = userAccountService.findById(createMobileApplicationDTO.getUserAccountId());

        MobileApplication mobileApplication = new MobileApplication();
        mobileApplication.setName(createMobileApplicationDTO.getName());
        mobileApplication.setUserAccount(userAccount);

        mobileApplicationRepository.save(mobileApplication);
        return mobileApplication;
    }
}
