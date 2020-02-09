package hr.ivlahek.showcase.rest.organization;

import hr.ivlahek.showcase.dto.organization.CreatTenantDTO;
import hr.ivlahek.showcase.exception.NotFoundException;
import hr.ivlahek.showcase.exception.messages.ExceptionMessage;
import hr.ivlahek.showcase.persistence.entity.Tenant;
import hr.ivlahek.showcase.persistence.repository.TenantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantService {

    @Autowired
    private TenantRepository TEnantRepository;

    private static final Logger logger = LoggerFactory.getLogger(TenantService.class);

    public Tenant findByExternalId(String organizationId) {
        Optional<Tenant> optionalOrganization = TEnantRepository.findByExternalId(organizationId);

        if (!optionalOrganization.isPresent()) {
            logger.error("Organization not found!");
            throw new NotFoundException(ExceptionMessage.ORGANIZATION_NOT_FOUND);
        }
        return optionalOrganization.get();
    }

    public Tenant create(CreatTenantDTO creatTenantDTO) {
        Tenant tenant = new Tenant();
        tenant.setName(creatTenantDTO.getName());
        tenant.setExternalId(creatTenantDTO.getExternalId());
        TEnantRepository.save(tenant);
        return tenant;
    }
}
