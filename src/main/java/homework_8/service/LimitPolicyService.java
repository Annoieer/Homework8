package homework_8.service;

import homework_8.dto.LimitPolicyDto;
import homework_8.dto.LimitPolicyResponseDto;
import homework_8.exception.CustomNotFoundException;
import homework_8.mapper.LimitPolicyMapper;
import homework_8.repository.LimitPolicyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LimitPolicyService {
    private final LimitPolicyRepository limitPolicyRepository;
    private final LimitPolicyMapper limitPolicyMapper;

    public LimitPolicyService(LimitPolicyRepository limitPolicyRepository, LimitPolicyMapper limitPolicyMapper) {
        this.limitPolicyRepository = limitPolicyRepository;
        this.limitPolicyMapper = limitPolicyMapper;
    }

    public LimitPolicyResponseDto getAllLimitPolicies() {
        List<LimitPolicyDto> limitPolicies = new ArrayList<>();
        limitPolicyRepository.findAll().forEach(limitPolicy -> limitPolicies.add(limitPolicyMapper.toDto(limitPolicy)));
        return new LimitPolicyResponseDto(limitPolicies);
    }

    public LimitPolicyDto getLimitPolicy(Long id) {
        return limitPolicyMapper.toDto(limitPolicyRepository.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Нет политики лимитов для id = " + id, HttpStatus.NOT_FOUND)));
    }

    public LimitPolicyDto setLimitPolicy(Long id, Long limit) {
        LimitPolicyDto limitPolicy = limitPolicyMapper.toDto(limitPolicyRepository.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Нет политики лимитов для id = " + id, HttpStatus.NOT_FOUND)));

        limitPolicyRepository.setLimitPolicy(id, limit);
        limitPolicy.setUserLimit(limit);
        return limitPolicy;
    }
}
