package homework_8.config;

import homework_8.service.MemoryService;
import homework_8.service.UserLimitService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class SchedulerConfig {
    private final UserLimitService userLimitService;
    private final MemoryService memoryService;

    public SchedulerConfig(UserLimitService userLimitService, MemoryService memoryService) {
        this.userLimitService = userLimitService;
        this.memoryService = memoryService;
    }

    @Scheduled(cron = "@daily")
    public void updateUserLimits() {
        userLimitService.updateUserLimits();
    }

    @Scheduled(cron = "@daily")
    public void deleteAllMemory() {
        memoryService.deleteAllMemory();
    }
}
