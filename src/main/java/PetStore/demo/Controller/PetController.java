package PetStore.demo.Controller;

import PetStore.demo.Models.Pet;
import PetStore.demo.Service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping("/create")
    public void createPets(){
        petService.createPet();

    }

    @GetMapping("/list")
    public List<Pet> listPets(){
        return petService.listPets();
    }


}
