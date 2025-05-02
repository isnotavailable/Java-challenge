package PetStore.demo.Service;

import PetStore.demo.Models.User;
import PetStore.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setFirstName("First" + i);
            user.setLastName("Last" + i);
            user.setEmail("user" + i + "@example.com");
            user.setBudget(BigDecimal.valueOf(random.nextInt(20) + 5));
            userRepository.save(user);
        }
    }

        public List<User> listUsers() {
            return userRepository.findAll();
        }

    }

