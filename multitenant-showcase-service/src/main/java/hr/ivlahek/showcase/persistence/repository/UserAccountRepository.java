package hr.ivlahek.showcase.persistence.repository;

import hr.ivlahek.showcase.persistence.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    List<UserAccount> findByFirstName(String firstName);
}
