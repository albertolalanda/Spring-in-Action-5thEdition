package pt.springinaction.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import pt.springinaction.tacocloud.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
    //CrudRepository first parameter entity type the repository is to persist
    //second parameter being the type of entity id property
}