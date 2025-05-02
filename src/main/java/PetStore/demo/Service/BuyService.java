package PetStore.demo.Service;

import PetStore.demo.Models.BuyHistory;
import PetStore.demo.Models.Pet;
import PetStore.demo.Models.User;
import PetStore.demo.Repository.BuyHistoryRepository;
import PetStore.demo.Repository.PetRepository;
import PetStore.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
    @RequiredArgsConstructor
    public class BuyService {

        private final PetRepository petRepository;
        private final UserRepository userRepository;
        private final PetService petService;
    private final BuyHistoryRepository buyHistoryRepository;

    @Transactional
        public void executeBuyProcess() {
            List<User> users = userRepository.findAll();

            List<Pet> availablePets = petRepository.findAll()
                    .stream()
                    .filter(pet -> pet.getOwner() == null)
                    .sorted(Comparator.comparing(Pet::getPrice))
                    .toList();


            int succesCount = 0;
            int failedCount = 0;

            for (Pet pet : availablePets){
                Optional<User> buyerOpt = users.stream()
                        .filter(user-> user.getBudget().compareTo(pet.getPrice())>=0)
                        .findFirst();

                if(buyerOpt.isPresent()){
                    User buyer = buyerOpt.get();
                    buyer.setBudget(buyer.getBudget().subtract(pet.getPrice()));
                    pet.setOwner(buyer);
                    userRepository.save(buyer);
                    petRepository.save(pet);
                    succesCount++;

                }
                else{
                    failedCount++;
                }



            }
            BuyHistory history = new BuyHistory();
            history.setExceutionTime(LocalDateTime.now());
            history.setSuccesCount(succesCount);
            history.setFailedCount(failedCount);

            buyHistoryRepository.save(history);

        }
    }
