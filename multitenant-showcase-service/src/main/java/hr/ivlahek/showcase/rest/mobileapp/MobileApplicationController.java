package hr.ivlahek.showcase.rest.mobileapp;

import hr.ivlahek.showcase.aop.InboundRequest;
import hr.ivlahek.showcase.aop.TenantId;
import hr.ivlahek.showcase.dto.mobileapp.CreateMobileApplicationDTO;
import hr.ivlahek.showcase.dto.mobileapp.MobileApplicationDTO;
import hr.ivlahek.showcase.dto.mobileapp.MobileApplicationEndPoints;
import hr.ivlahek.showcase.persistence.entity.MobileApplication;
import hr.ivlahek.showcase.persistence.repository.MobileApplicationRepository;
import hr.ivlahek.showcase.rest.organization.TenantController;
import hr.ivlahek.showcase.rest.organization.TenantService;
import hr.ivlahek.showcase.rest.useraccount.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
@Transactional
public class MobileApplicationController {

    @Autowired
    private TenantService tenantService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private MobileApplicationRepository mobileApplicationRepository;
    @Autowired
    private MobileApplicationService mobileApplicationService;

    private static final Logger logger = LoggerFactory.getLogger(TenantController.class);

    @PostMapping(path = MobileApplicationEndPoints.MOBILE_APPLICATION_RESOURCE)
    @InboundRequest
    public MobileApplicationDTO createMobileApplication(
            @PathVariable("organizationId") @TenantId String organizationId,
            @RequestBody CreateMobileApplicationDTO createMobileApplicationDTO) {
        return map(mobileApplicationService.create(createMobileApplicationDTO));
    }


    @GetMapping(path = MobileApplicationEndPoints.MOBILE_APPLICATION_RESOURCE_BY_ID)
    @InboundRequest
    public MobileApplicationDTO getMobileApplicationById(@PathVariable("organizationId") @TenantId String organizationId, @PathVariable("id") int mobileAppId) {
        return map(mobileApplicationService.findById(mobileAppId));
    }

    private MobileApplicationDTO map(MobileApplication mobileApplication) {
        MobileApplicationDTO mobileApplicationDTO = new MobileApplicationDTO();
        mobileApplicationDTO.setName(mobileApplication.getName());
        mobileApplicationDTO.setId(mobileApplication.getId());
        mobileApplicationDTO.setOrganizationId(mobileApplication.getTenant().getExternalId());
        mobileApplicationDTO.setUserId(mobileApplication.getUserAccount().getId());
        return mobileApplicationDTO;
    }
}
