package msn.example.security.user;

import msn.example.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {


    Optional<User> findById(String email);
}
