package application.service;

import application.controller.AuthorizationMapHolder;
import application.controller.HasLogout;
import application.controller.UserRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AutorizationServiceImpl implements AutorizationService {

    @Autowired
    protected AuthorizationMapHolder mapHolder;

    @Autowired
    protected HasLogout logout;

    @Override
    @Scheduled(fixedDelay = 1000, fixedRate = 60000)
    public boolean checkTheInaction(Date date1, Date date2) {
        //TODO вот это выполнить в цикле для всех элементов мапы, мапу получаем через инжектированный сервис
        mapHolder.getAuthorizationMap().entrySet().stream().filter(entry -> {})
        long diff = (date1.getTime() - date2.getTime())/60000;
        if (diff >= 30) {
            return true;
        }
        //TODO добавить удаление из мапы
        logout.logout()
        return false;
    }
}
