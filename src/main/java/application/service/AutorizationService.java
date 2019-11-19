package application.service;

import java.util.Date;
import java.util.HashMap;

public interface AutorizationService {
    boolean scheduleFixedDelayTask(Date date1, Date date2, HashMap map);
}
