package com.HMCTeam.KeepNotes;

import com.HMCTeam.KeepNotes.models.User;
import com.HMCTeam.KeepNotes.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("hellotest2@gmail.com");
        user.setPassword("helloPassword2");
        user.setFirstName("mohamed");
        user.setLastName("ahmed");

        User savedUser = userRepository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getUserId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testUserByEmail(){
        String email = "h@gmail.com";
        User user = userRepository.findUserByEmail(email);

        assertThat(user).isNotNull();
    }

}
