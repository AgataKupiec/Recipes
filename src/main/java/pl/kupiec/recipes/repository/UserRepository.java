package pl.kupiec.recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kupiec.recipes.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {


}
