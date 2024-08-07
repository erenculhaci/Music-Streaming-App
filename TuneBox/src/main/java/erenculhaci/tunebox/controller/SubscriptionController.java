package erenculhaci.tunebox.controller;

import erenculhaci.tunebox.dto.SubscriptionDTO;
import erenculhaci.tunebox.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/createSubscription")
    public ResponseEntity<Boolean> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        Boolean created = subscriptionService.createSubscription(subscriptionDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/getSubscriptionById")
    public ResponseEntity<SubscriptionDTO> getSubscriptionById(@RequestParam Long id) {
        SubscriptionDTO subscriptionDTO = subscriptionService.getSubscriptionById(id);
        return new ResponseEntity<>(subscriptionDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllSubscriptions")
    public ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions() {
        List<SubscriptionDTO> subscriptionDTOList = subscriptionService.getAllSubscriptions();
        return new ResponseEntity<>(subscriptionDTOList, HttpStatus.OK);
    }

    @GetMapping("/getSubscriptionByUserId")
    public ResponseEntity<SubscriptionDTO> getSubscriptionByUserId(@RequestParam Long userId) {
        SubscriptionDTO subscriptionDTO = subscriptionService.getSubscriptionByUserId(userId);
        return new ResponseEntity<>(subscriptionDTO, HttpStatus.OK);
    }

    @GetMapping("/getSubscriptionByUsername")
    public ResponseEntity<SubscriptionDTO> getSubscriptionByUsername(@RequestParam String username) {
        SubscriptionDTO subscriptionDTO = subscriptionService.getSubscriptionByUsername(username);
        return new ResponseEntity<>(subscriptionDTO, HttpStatus.OK);
    }

    @GetMapping("/getSubscriptionCountByType")
    public ResponseEntity<Long> getSubscriptionCountByType(@RequestParam String type) {
        Long count = subscriptionService.getSubscriptionCountByType(type);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PutMapping("/updateSubscription")
    public ResponseEntity<Boolean> updateSubscription(@RequestParam Long id, @RequestBody SubscriptionDTO subscriptionDTO) {
        Boolean updated = subscriptionService.updateSubscription(id, subscriptionDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSubscription")
    public ResponseEntity<Void> deleteSubscription(@RequestParam Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }
}
