package hr.ivlahek.showcase.event;

import hr.ivlahek.showcase.aop.InboundRequest;
import hr.ivlahek.showcase.aop.TenantId;
import hr.ivlahek.showcase.dto.mobileapp.CreateMobileApplicationDTO;
import hr.ivlahek.showcase.dto.user.CreateUserAccountDTO;
import hr.ivlahek.showcase.rest.mobileapp.MobileApplicationService;
import hr.ivlahek.showcase.rest.useraccount.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageHandler {
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private MobileApplicationService mobileApplicationService;

    @InboundRequest
    public void create(@TenantId String organizationId, CreateMobileApplicationDTO createMobileApplicationDTO) {
        mobileApplicationService.create(createMobileApplicationDTO);
    }

    @InboundRequest
    public void create(CreateUserAccountDTO createUserAccountDTO, @TenantId String organizationId) {
        userAccountService.create(createUserAccountDTO);
    }
}
