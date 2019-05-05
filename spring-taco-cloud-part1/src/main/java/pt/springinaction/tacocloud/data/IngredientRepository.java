package pt.springinaction.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import pt.springinaction.tacocloud.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    //CrudRepository first parameter entity type the repository is to persist
    //second parameter being the type of entity id property
}