package homework_8.controller;

import homework_8.dto.LimitPolicyDto;
import homework_8.dto.LimitPolicyResponseDto;
import homework_8.service.LimitPolicyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/policy")
public class LimitPolicyController {

    private final LimitPolicyService limitPolicyService;

    public LimitPolicyController(LimitPolicyService limitPolicyService) {
        this.limitPolicyService = limitPolicyService;
    }

    @GetMapping(value = "/all")
    public LimitPolicyResponseDto getAllLimitPolicies() {
        return limitPolicyService.getAllLimitPolicies();
    }

    @GetMapping(value = "/{id}")
    public LimitPolicyDto getById(@PathVariable Long id) {
        return limitPolicyService.getLimitPolicy(id);
    }

    @PostMapping(value = "/{id}")
    public LimitPolicyDto changeLimit(@PathVariable Long id, @RequestParam Long limit) {
        return limitPolicyService.setLimitPolicy(id, limit);
    }
}
