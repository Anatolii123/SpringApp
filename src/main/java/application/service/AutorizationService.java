package application.service;

import java.util.Date;

public interface AutorizationService {
    boolean checkTheInaction(Date date1, Date date2);
}
