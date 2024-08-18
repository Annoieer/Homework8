package homework_8.config;

import homework_8.service.TransactionalMemoryService;
import homework_8.service.UserLimitService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class SchedulerConfig {
    private final UserLimitService userLimitService;
    private final TransactionalMemoryService transactionalMemoryService;

    public SchedulerConfig(UserLimitService userLimitService, TransactionalMemoryService transactionalMemoryService) {
        this.userLimitService = userLimitService;
        this.transactionalMemoryService = transactionalMemoryService;
    }

    @Scheduled(cron = "@daily")
    public void updateUserLimits() {
        userLimitService.updateUserLimits();
    }

    @Scheduled(cron = "@daily")
    public void deleteAllMemory() {
        transactionalMemoryService.deleteAllMemory();
    }
}
