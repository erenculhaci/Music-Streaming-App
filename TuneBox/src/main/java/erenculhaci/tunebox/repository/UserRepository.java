package erenculhaci.tunebox.repository;

import erenculhaci.tunebox.entity.Subscription;
import erenculhaci.tunebox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllBySubscriptionIn(List<Subscription> subscriptions);
}
