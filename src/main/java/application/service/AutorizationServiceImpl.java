package application.service;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.HashMap;

public class AutorizationServiceImpl implements AutorizationService {

    @Override
    @Scheduled(fixedDelay = 1000, fixedRate = 60000)
    public boolean scheduleFixedDelayTask(Date date1, Date date2, HashMap map) {
        return false;
    }
}
