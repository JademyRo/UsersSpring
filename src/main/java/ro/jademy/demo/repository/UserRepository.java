package ro.jademy.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.jademy.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
