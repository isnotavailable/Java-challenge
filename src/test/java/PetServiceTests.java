import PetStore.demo.Models.Pet;
import PetStore.demo.Models.User;
import PetStore.demo.Repository.PetRepository;
import PetStore.demo.Repository.UserRepository;
import PetStore.demo.Service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PetServiceTests {

    @Mock
    private PetRepository petRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PetService petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListPetsReturnsAllPets() {
        // Arrange
        List<Pet> petList = new ArrayList<>();
        Pet pet = new Pet();
        pet.setName("Test Pet");
        petList.add(pet);

        when(petRepository.findAll()).thenReturn(petList);

        // Act
        List<Pet> result = petService.listPets();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test Pet", result.get(0).getName());
    }

    @Test
    void testCreatePetSavesPetsAndUsers() {
        // Arrange
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(petRepository.save(any(Pet.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        petService.createPet();

        // Assert
        verify(userRepository, atLeastOnce()).save(any(User.class));
        verify(petRepository, atLeast(20)).save(any(Pet.class));
    }
}

