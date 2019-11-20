package application.service;

import org.springframework.scheduling.annotation.Scheduled;

public interface AutorizationService {
    @Scheduled(fixedRate = 60000)
    boolean checkTheInaction();
}
