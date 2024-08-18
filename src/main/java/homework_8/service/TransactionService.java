package homework_8.service;

import homework_8.dto.TransactionResponseDto;
import homework_8.dto.TransactionalMemoryDto;
import homework_8.dto.TransactionalMemoryResponseDto;
import homework_8.dto.UserLimitDto;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final UserLimitService userLimitService;
    private final TransactionalMemoryService transactionalMemoryService;

    public TransactionService(UserLimitService userLimitService,
                              TransactionalMemoryService transactionalMemoryService) {
        this.userLimitService = userLimitService;
        this.transactionalMemoryService = transactionalMemoryService;
    }

    public TransactionalMemoryDto getTransaction(Long id) {
        return transactionalMemoryService.getTransactionalMemory(id);
    }

    public TransactionalMemoryResponseDto getAllTransactions(){
        return transactionalMemoryService.getAllTransactionalMemories();
    }

    public TransactionalMemoryResponseDto getAllTransactionsByUser(Long userId) {
        return transactionalMemoryService.getTransactionalMemoryByUserId(userId);
    }

    public TransactionResponseDto executeTransaction(Long userId, Long amount) {
        UserLimitDto userLimit = userLimitService.changeUserLimitByAmount(userId, amount);
        transactionalMemoryService.saveTransaction(userId, amount);
        return new TransactionResponseDto("Транзакция пользователя с id = " + userId + " на сумму " + amount + " проведена успешно!",
                userLimit);
    }

    public TransactionResponseDto cancelTransaction(Long id) {
        TransactionalMemoryDto memory = transactionalMemoryService.cancelTransaction(id);
        Long userId = memory.getUserId();
        Long amount = memory.getAmount();

        UserLimitDto userLimit = userLimitService.changeUserLimitByAmount(userId, -amount);

        return new TransactionResponseDto( "Лимит пользователя с id = " + userId + " успешно восстановлен",
                userLimit);
    }
}
