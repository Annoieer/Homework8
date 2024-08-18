package homework_8.service;

import homework_8.dto.TransactionalMemoryDto;
import homework_8.dto.TransactionalMemoryResponseDto;
import homework_8.entity.TransactionalMemory;
import homework_8.exception.CustomNotFoundException;
import homework_8.mapper.TransactionalMemoryMapper;
import homework_8.repository.TransactionalMemoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class TransactionalMemoryService {
    private final TransactionalMemoryRepository transactionalMemoryRepository;
    private final TransactionalMemoryMapper transactionalMemoryMapper;

    public TransactionalMemoryService(TransactionalMemoryRepository transactionalMemoryRepository, TransactionalMemoryMapper transactionalMemoryMapper) {
        this.transactionalMemoryRepository = transactionalMemoryRepository;
        this.transactionalMemoryMapper = transactionalMemoryMapper;
    }

    public TransactionalMemoryResponseDto getAllTransactionalMemories() {
        List<TransactionalMemoryDto> memories = new ArrayList<>();
        transactionalMemoryRepository.findAll().forEach(limitPolicy -> memories.add(transactionalMemoryMapper.toDto(limitPolicy)));
        return new TransactionalMemoryResponseDto(memories);
    }

    public TransactionalMemoryDto getTransactionalMemory(Long id) {
        return transactionalMemoryMapper.toDto(transactionalMemoryRepository.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Транзакции с id = " + id + " не существует!", HttpStatus.NOT_FOUND)));
    }

    public TransactionalMemoryResponseDto getTransactionalMemoryByUserId(Long userId) {
        List<TransactionalMemoryDto> memories = new ArrayList<>();
        transactionalMemoryRepository.findByUserId(userId)
                .forEach(limitPolicy -> memories.add(transactionalMemoryMapper.toDto(limitPolicy)));
        return new TransactionalMemoryResponseDto(memories);
    }

    public TransactionalMemoryDto cancelTransaction(Long id) {
        TransactionalMemoryDto memory = getTransactionalMemory(id);
        if (memory.getIsCancelled()) {
            throw new CustomNotFoundException("Транзакция уже была отменена!", HttpStatus.PAYMENT_REQUIRED);
        }
        transactionalMemoryRepository.cancelTransaction(id);
        memory.setIsCancelled(true);
        return memory;
    }

    public void saveTransaction(Long userId, Long amount) {
        Date date = new Date();
        TransactionalMemory transaction = new TransactionalMemory(amount, userId, date);
        transactionalMemoryRepository.save(transaction);
    }

    public void deleteAllMemory() {
        transactionalMemoryRepository.deleteAll();
    }
}
