package PetStore.demo.Repository;

import PetStore.demo.Models.BuyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyHistoryRepository extends JpaRepository<BuyHistory,Long> {
}
