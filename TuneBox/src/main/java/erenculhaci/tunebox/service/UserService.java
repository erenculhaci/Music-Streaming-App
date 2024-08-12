package erenculhaci.tunebox.service;
import erenculhaci.tunebox.dto.UserDTO;
import erenculhaci.tunebox.dto.UserResponseDTO;
import erenculhaci.tunebox.entity.Subscription;
import erenculhaci.tunebox.entity.User;
import erenculhaci.tunebox.repository.SubscriptionRepository;
import erenculhaci.tunebox.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final SubscriptionRepository subscriptionRepository;

    public Boolean createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return true;
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return convertToResponseDTO(user);
    }

    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return convertToResponseDTO(user);
    }

    public List<UserResponseDTO> getAllUsersBySubscription(String subscriptionType) {
        List<Subscription> AllSubscriptionsByType = subscriptionRepository.findAllByType(subscriptionType);
        return userRepository.findAllBySubscriptionIn(AllSubscriptionsByType).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());

    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Boolean updateUser(String username, String currentPassword, UserDTO userDTO) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getPassword().equals(currentPassword)) {
            throw new IllegalArgumentException("Password is incorrect");
        }

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());

        user = userRepository.save(user);

        return true;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponseDTO convertToResponseDTO(User user) {
        return UserResponseDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
