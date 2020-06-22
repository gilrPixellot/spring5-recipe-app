package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;
    private String description;

}
