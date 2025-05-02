package PetStore.demo.Service;

import PetStore.demo.Models.Pet;
import PetStore.demo.Models.PetType;
import PetStore.demo.Models.User;
import PetStore.demo.Repository.PetRepository;
import PetStore.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public void createPet() {
        Random random = new Random();
        String[] firstNames = {"Alice", "Bob", "Charlie", "Daisy", "Eve"};
        String[] lastNames = {"Smith", "Johnson", "Brown", "Williams", "Jones"};

        for (int i = 0; i < 20; i++) {
            Pet pet = new Pet();
            pet.setName("Pet " + i);
            pet.setType(random.nextBoolean() ? PetType.CAT : PetType.DOG);
            pet.setDescription("Lovely " + pet.getType());
            pet.setDateOfBirth(LocalDate.now().minusYears(random.nextInt(10))); // 0-9 years old

            // Create and save Owner (User)
            User owner = new User();
            owner.setFirstName(firstNames[random.nextInt(firstNames.length)]);
            owner.setLastName(lastNames[random.nextInt(lastNames.length)]);
            owner.setEmail(owner.getFirstName().toLowerCase() + "." + owner.getLastName().toLowerCase() + "@example.com");
            owner.setBudget(BigDecimal.valueOf(1000 + random.nextInt(4000))); // 1000 - 4999
            User savedUser = userRepository.save(owner);

            // Assign owner
            pet.setOwner(savedUser);

            // Set price and rating
            int age = calculateAge(pet.getDateOfBirth());

            if (pet.getType() == PetType.CAT) {
                pet.setPrice(BigDecimal.valueOf(age));
            } else { // Dog
                int rating = random.nextInt(11); // 0-10
                pet.setRating(rating);
                pet.setPrice(BigDecimal.valueOf(age + rating));
            }

            petRepository.save(pet);
        }
    }

    public List<Pet> listPets() {
        return petRepository.findAll();
    }

    private int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return 0;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
