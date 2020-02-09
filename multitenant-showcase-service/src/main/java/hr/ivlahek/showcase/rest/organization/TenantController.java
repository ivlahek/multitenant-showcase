package hr.ivlahek.showcase.rest.organization;

import hr.ivlahek.showcase.dto.organization.CreatTenantDTO;
import hr.ivlahek.showcase.dto.organization.TenantDTO;
import hr.ivlahek.showcase.dto.organization.OrganizationEndPoints;
import hr.ivlahek.showcase.persistence.entity.Tenant;
import hr.ivlahek.showcase.persistence.repository.TenantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TenantController {

    @Autowired
    private TenantRepository TEnantRepository;
    @Autowired
    private TenantService tenantService;

    private static final Logger logger = LoggerFactory.getLogger(TenantController.class);

    @PostMapping(path = OrganizationEndPoints.TENANT_RESOURCE)
    public TenantDTO createTenant(@RequestBody CreatTenantDTO creatTenantDTO) {
        return map(tenantService.create(creatTenantDTO));
    }

    @GetMapping(path = OrganizationEndPoints.TENANT_RESOURCE_ID)
    public TenantDTO getTenantById(@PathVariable String id) {
        return map(tenantService.findByExternalId(id));
    }

    private TenantDTO map(Tenant tenant) {
        TenantDTO tenantDTO = new TenantDTO();
        tenantDTO.setId(tenant.getExternalId());
        tenantDTO.setName(tenant.getName());
        return tenantDTO;
    }
}
