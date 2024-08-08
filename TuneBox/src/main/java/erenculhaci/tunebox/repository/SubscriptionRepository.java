package erenculhaci.tunebox.repository;

import erenculhaci.tunebox.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Subscription findByUserId(Long userId);
    Long countByType(String type);
    List<Subscription> findAllByType(String type);
}

