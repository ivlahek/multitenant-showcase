package hr.ivlahek.showcase.persistence.repository;

import hr.ivlahek.showcase.persistence.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    Optional<Organization> findByExternalId(String companyId);
}
