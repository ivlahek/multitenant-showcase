package hr.ivlahek.showcase.persistence.repository;

import hr.ivlahek.showcase.persistence.entity.UserAccount;
import hr.ivlahek.showcase.persistence.entity.UserAccountBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAccountRepositoryTest extends RepositoryTest {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Test
    public void should_create_user() {
        //BUILD
        UserAccount userAccount = UserAccountBuilder.anUser().build();

        //OPERATE
        userAccountRepository.save(userAccount);


    }


}