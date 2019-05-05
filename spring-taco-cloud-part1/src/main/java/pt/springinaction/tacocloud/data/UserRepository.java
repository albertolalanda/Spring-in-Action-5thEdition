package pt.springinaction.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import pt.springinaction.tacocloud.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
