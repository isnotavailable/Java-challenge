package PetStore.demo.Service;

import PetStore.demo.Models.BuyHistory;
import PetStore.demo.Repository.BuyHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class HistoryService {

    private final BuyHistoryRepository buyHistoryRepository;

    public List<BuyHistory> getBuyHistory() {
       return buyHistoryRepository.findAll();

    }
}
