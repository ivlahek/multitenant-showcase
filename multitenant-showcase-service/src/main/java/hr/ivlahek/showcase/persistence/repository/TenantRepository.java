package hr.ivlahek.showcase.persistence.repository;

import hr.ivlahek.showcase.persistence.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    Optional<Tenant> findByExternalId(String companyId);
}
