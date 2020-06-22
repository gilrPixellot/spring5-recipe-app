package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe") // means it cannot be exist without the recipe. its not independent
    private Set<Ingredient> ingredientSet = new HashSet<>();

    @Lob // longer then 256 chars ( large object)
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL) // delete this aswell when delete recipe
    private Notes notes;

    @Enumerated(value = EnumType.STRING) // save in DB as string and not as number
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(name = "recipe_category",
    joinColumns = @JoinColumn(name = "recipe_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredientSet.add(ingredient);
        return this;
    }
    //endregion
}
