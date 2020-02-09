package hr.ivlahek.showcase.rest.organization;

import hr.ivlahek.showcase.dto.organization.CreateOrganizationDTO;
import hr.ivlahek.showcase.exception.NotFoundException;
import hr.ivlahek.showcase.exception.messages.ExceptionMessage;
import hr.ivlahek.showcase.persistence.entity.Organization;
import hr.ivlahek.showcase.persistence.repository.OrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);

    public Organization findByExternalId(String organizationId) {
        Optional<Organization> optionalOrganization = organizationRepository.findByExternalId(organizationId);

        if (!optionalOrganization.isPresent()) {
            logger.error("Organization not found!");
            throw new NotFoundException(ExceptionMessage.ORGANIZATION_NOT_FOUND);
        }
        return optionalOrganization.get();
    }

    public Organization create(CreateOrganizationDTO createOrganizationDTO) {
        Organization organization = new Organization();
        organization.setName(createOrganizationDTO.getName());
        organization.setExternalId(createOrganizationDTO.getExternalId());
        organizationRepository.save(organization);
        return organization;
    }
}
