package homework_8.service;

import homework_8.dto.MemoryDto;
import homework_8.entity.Memory;
import homework_8.exception.CustomNotFoundException;
import homework_8.mapper.MemoryMapper;
import homework_8.repository.MemoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class MemoryService {
    private final MemoryRepository memoryRepository;
    private final MemoryMapper memoryMapper;

    public MemoryService(MemoryRepository memoryRepository, MemoryMapper memoryMapper) {
        this.memoryRepository = memoryRepository;
        this.memoryMapper = memoryMapper;
    }

    public MemoryDto getMemory(Long id) {
        return memoryMapper.toDto(memoryRepository.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Операции изменения лимита с id = " + id + " не существует!", HttpStatus.NOT_FOUND)));
    }

    public MemoryDto cancelChangeLimitMemory(Long id) {
        MemoryDto memory = getMemory(id);
        if (memory.getIsCancelled()) {
            throw new CustomNotFoundException("Операция изменения лимита уже была отменена!", HttpStatus.PAYMENT_REQUIRED);
        }
        memoryRepository.cancelUserLimitChange(id);
        memory.setIsCancelled(true);
        return memory;
    }

    public void saveChangeLimitMemory(Long userId, Long amount) {
        Date date = new Date();
        Memory memory = new Memory(amount, userId, date);
        memoryRepository.save(memory);
    }

    public void deleteAllMemory() {
        memoryRepository.deleteAll();
    }
}
