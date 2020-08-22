package hr.ivlahek.showcase.persistence.repository;

import hr.ivlahek.showcase.persistence.entity.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserAccountRepository extends CustomRepository<UserAccount, Integer> {

    List<UserAccount> findByFirstName(String firstName);

}
