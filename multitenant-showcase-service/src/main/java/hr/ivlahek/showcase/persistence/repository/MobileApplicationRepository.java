package hr.ivlahek.showcase.persistence.repository;

import hr.ivlahek.showcase.persistence.entity.MobileApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MobileApplicationRepository extends JpaRepository<MobileApplication, Integer> {

    List<MobileApplication> findByName(String name);
}
