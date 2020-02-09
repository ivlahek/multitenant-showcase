package hr.ivlahek.showcase.persistence.repository;

import hr.ivlahek.showcase.persistence.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    Optional<UserAccount> findByIdAndOrganizationId(int userAccountId, int organizationId);

    List<UserAccount> findByFirstName(String firstName);
}
