package homework_8.controller;

import homework_8.dto.CancelUserLimitChangeResponseDto;
import homework_8.dto.UserLimitDto;
import homework_8.dto.UserLimitResponseDto;
import homework_8.service.UserLimitService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/limits")
public class UserLimitsController {

    private final UserLimitService userLimitService;

    public UserLimitsController(UserLimitService userLimitService) {
        this.userLimitService = userLimitService;
    }

    @GetMapping(value = "/all")
    public UserLimitResponseDto getAllUserLimits() {
        return userLimitService.getAllUserLimits();
    }

    @GetMapping(value = "/{id}")
    public UserLimitDto getById(@PathVariable Long id) {
        return userLimitService.getById(id);
    }

    @GetMapping(value = "/user/{userId}")
    public UserLimitDto getByUserId(@PathVariable Long userId) {
        return userLimitService.getByUserId(userId);
    }

    @PostMapping(value = "/user/{userId}/updateLimitPolicy")
    public UserLimitDto changeUserLimitPolicy(@PathVariable Long userId, @RequestParam Long limitPolicy) {
        return userLimitService.changeUserLimitPolicy(userId, limitPolicy);
    }

    @PostMapping(value = "/update/user/{userId}")
    public UserLimitDto updateUserLimit(@PathVariable Long userId, @RequestParam Long amount) {
        return userLimitService.changeUserLimitByAmount(userId, amount);
    }

    @PostMapping(value = "/update/cancelChange/{id}")
    public CancelUserLimitChangeResponseDto cancelUserLimitMemory(@PathVariable Long id) {
        return userLimitService.cancelUserLimitMemoryById(id);
    }
}
