package homework_8.controller;

import homework_8.dto.TransactionResponseDto;
import homework_8.dto.TransactionalMemoryDto;
import homework_8.dto.TransactionalMemoryResponseDto;
import homework_8.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/all")
    public TransactionalMemoryResponseDto getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping(value = "/{id}")
    public TransactionalMemoryDto getById(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @GetMapping(value = "/user/{userId}")
    public TransactionalMemoryResponseDto getByUserId(@PathVariable Long userId) {
        return transactionService.getAllTransactionsByUser(userId);
    }

    @PostMapping(value = "/manage/execute/{userId}")
    public TransactionResponseDto executeTransaction(@PathVariable Long userId, @RequestParam Long amount) {
        return transactionService.executeTransaction(userId, amount);
    }

    @PostMapping(value = "/manage/cancel/{id}")
    public TransactionResponseDto cancelTransaction(@PathVariable Long id) {
        return transactionService.cancelTransaction(id);
    }
}
