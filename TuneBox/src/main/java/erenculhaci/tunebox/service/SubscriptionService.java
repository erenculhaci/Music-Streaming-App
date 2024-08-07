package erenculhaci.tunebox.service;

import erenculhaci.tunebox.dto.SubscriptionDTO;
import erenculhaci.tunebox.entity.Subscription;
import erenculhaci.tunebox.entity.User;
import erenculhaci.tunebox.repository.SubscriptionRepository;
import erenculhaci.tunebox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public Boolean createSubscription(SubscriptionDTO subscriptionDTO) {
        User user = userRepository.findById(subscriptionDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Subscription subscription = Subscription.builder()
                .type(subscriptionDTO.getType())
                .startDate(subscriptionDTO.getStartDate())
                .endDate(subscriptionDTO.getEndDate())
                .user(user)
                .build();

        subscriptionRepository.save(subscription);

        return true;
    }

    public SubscriptionDTO getSubscriptionById(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));
        return convertToDTO(subscription);
    }

    public List<SubscriptionDTO> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public SubscriptionDTO getSubscriptionByUserId(Long userId) {
        Subscription subscription = subscriptionRepository.findByUserId(userId);
        return convertToDTO(subscription);
    }

    public SubscriptionDTO getSubscriptionByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Subscription subscription = subscriptionRepository.findByUserId(user.getId());
        return convertToDTO(subscription);
    }

    public Long getSubscriptionCountByType(String type) {
        return subscriptionRepository.countByType(type);
    }

    public Boolean updateSubscription(Long id, SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));

        User user = userRepository.findById(subscriptionDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        subscription.setType(subscriptionDTO.getType());
        subscription.setStartDate(subscriptionDTO.getStartDate());
        subscription.setEndDate(subscriptionDTO.getEndDate());
        subscription.setUser(user);

        subscriptionRepository.save(subscription);

        return true;
    }

    public void deleteSubscription(Long id) {
        subscriptionRepository.deleteById(id);
    }

    private SubscriptionDTO convertToDTO(Subscription subscription) {
        return SubscriptionDTO.builder()
                .type(subscription.getType())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .userId(subscription.getUser().getId())
                .build();
    }
}
