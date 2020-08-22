package hr.ivlahek.showcase.persistence.repository;

import hr.ivlahek.showcase.persistence.entity.MobileApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MobileApplicationRepository extends CustomRepository<MobileApplication, Integer> {
    Optional<MobileApplication> findById(MobileApplication id);

    List<MobileApplication> findByName(String name);
}
