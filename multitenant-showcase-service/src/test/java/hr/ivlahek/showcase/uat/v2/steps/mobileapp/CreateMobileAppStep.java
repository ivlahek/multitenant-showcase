package hr.ivlahek.showcase.uat.v2.steps.mobileapp;

import hr.ivlahek.showcase.dto.mobileapp.MobileApplicationDTO;
import hr.ivlahek.showcase.dto.user.UserAccountDTO;

public interface CreateMobileAppStep {

    MobileApplicationDTO createMobileApp(UserAccountDTO userAccountDTO) throws InterruptedException;
}
