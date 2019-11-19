package application.service;

import org.springframework.scheduling.annotation.Scheduled;
import java.util.Date;

public class AutorizationServiceImpl implements AutorizationService {

    @Override
    @Scheduled(fixedDelay = 1000, fixedRate = 60000)
    public boolean checkTheInaction(Date date1, Date date2) {
        long diff = (date1.getTime() - date2.getTime())/60000;
        if (diff >= 30) {
            return true;
        }
        return false;
    }
}
