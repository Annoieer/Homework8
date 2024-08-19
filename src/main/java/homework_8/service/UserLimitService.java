package homework_8.service;

import homework_8.dto.*;
import homework_8.entity.UserLimit;
import homework_8.exception.CustomNotFoundException;
import homework_8.exception.CustomPaymentRequiredException;
import homework_8.mapper.UserLimitMapper;
import homework_8.repository.UserLimitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserLimitService {
    private final Long DEFAULT_USER_LIMIT_POLICY = 1L;
    private final LimitPolicyService limitPolicyService;
    private final UserLimitMapper userLimitMapper;
    private final UserLimitRepository userLimitRepository;
    private final MemoryService memoryService;

    public UserLimitService(LimitPolicyService limitPolicyService,
                            UserLimitMapper userLimitMapper,
                            UserLimitRepository userLimitRepository,
                            MemoryService memoryService) {
        this.limitPolicyService = limitPolicyService;
        this.userLimitMapper = userLimitMapper;
        this.userLimitRepository = userLimitRepository;
        this.memoryService = memoryService;
    }

    public UserLimitResponseDto getAllUserLimits() {
        List<UserLimitDto> limits = new ArrayList<>();
        userLimitRepository.findAll().forEach(user -> limits.add(userLimitMapper.toDto(user)));
        return new UserLimitResponseDto(limits);
    }

    public UserLimitDto getById(Long id) {
        return userLimitMapper.toDto(userLimitRepository.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Нет лимитов с id = " + id, HttpStatus.NOT_FOUND)));
    }

    public UserLimitDto getByUserId(Long userId) {
        UserLimit userLimit;
        userLimit = userLimitRepository.findByUserId(userId);
        if (userLimit == null) {
            userLimit = addNewUserLimit(userId, DEFAULT_USER_LIMIT_POLICY);
        }
        return userLimitMapper.toDto(userLimit);
    }

    public UserLimitDto changeUserLimitPolicy(Long userId, Long limitPolicy) {
        UserLimit userLimit;
        try {
            userLimit = userLimitRepository.findByUserId(userId);
            userLimitRepository.setUserLimitPolicy(userId, limitPolicy);
            userLimit.setPolicyLevel(limitPolicy);
        } catch (NullPointerException exception) {
            userLimit = addNewUserLimit(userId, limitPolicy);
        }
        return userLimitMapper.toDto(userLimit);
    }

    public UserLimitDto changeUserLimitByAmount(Long userId, Long amount) {
        UserLimit userLimit = userLimitRepository.findByUserId(userId);
        if (userLimit == null) {
            userLimit = addNewUserLimit(userId, DEFAULT_USER_LIMIT_POLICY);
        }

        Long oldLimit = userLimit.getUserLimit();
        Long newLimit = oldLimit - amount;
        if (newLimit < 0) {
            throw new CustomPaymentRequiredException("Превышен дневной лимит операций! Остаток " + oldLimit,
                    HttpStatus.PAYMENT_REQUIRED);
        }

        userLimitRepository.setUserLimit(userId, newLimit);
        memoryService.saveChangeLimitMemory(userId, amount);
        userLimit.setUserLimit(newLimit);

        return userLimitMapper.toDto(userLimit);
    }

    public CancelUserLimitChangeResponseDto cancelUserLimitMemoryById(Long id) {
        MemoryDto memory = memoryService.cancelChangeLimitMemory(id);
        Long userId = memory.getUserId();
        Long amount = memory.getAmount();

        UserLimitDto userLimit = changeUserLimitByAmount(userId, -amount);

        return new CancelUserLimitChangeResponseDto("Лимит пользователя с id = " + userId + " успешно восстановлен ",
                userLimit);
    }

    public void updateUserLimits() {
        List<LimitPolicyDto> polices = limitPolicyService.getAllLimitPolicies().getLimitPolices();
        polices.forEach(policy -> userLimitRepository.changeUsersLimitByPolicy(policy.getUserLimit(), policy.getId()));
    }

    private UserLimit addNewUserLimit(Long userId, Long limitPolicy) {
        Long dailyLimit = limitPolicyService.getLimitPolicy(limitPolicy).getUserLimit();
        UserLimit userLimit = new UserLimit(userId, dailyLimit, limitPolicy);
        userLimitRepository.save(userLimit);
        userLimit = userLimitRepository.findByUserId(userId);
        return userLimit;
    }
}
