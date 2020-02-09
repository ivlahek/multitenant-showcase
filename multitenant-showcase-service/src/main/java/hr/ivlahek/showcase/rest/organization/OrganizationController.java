package hr.ivlahek.showcase.rest.organization;

import hr.ivlahek.showcase.dto.organization.CreateOrganizationDTO;
import hr.ivlahek.showcase.dto.organization.OrganizationDTO;
import hr.ivlahek.showcase.dto.organization.OrganizationEndPoints;
import hr.ivlahek.showcase.persistence.entity.Organization;
import hr.ivlahek.showcase.persistence.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class OrganizationController {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private OrganizationService organizationService;

    private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @PostMapping(path = OrganizationEndPoints.ORGANIZATION_RESOURCE)
    public OrganizationDTO createOrganization(@RequestBody CreateOrganizationDTO createOrganizationDTO) {
        return map(organizationService.create(createOrganizationDTO));
    }

    @GetMapping(path = OrganizationEndPoints.ORGANIZATION_RESOURCE_ID)
    public OrganizationDTO getOrganizationById(@PathVariable String id) {
        return map(organizationService.findByExternalId(id));
    }

    private OrganizationDTO map(Organization organization) {
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setId(organization.getExternalId());
        organizationDTO.setName(organization.getName());
        return organizationDTO;
    }
}
