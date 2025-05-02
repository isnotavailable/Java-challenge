package PetStore.demo.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private PetType type;
    private String description;
    private LocalDate dateOfBirth;
    private Integer rating;
    private BigDecimal price;

    @ManyToOne
    private User owner;
}
