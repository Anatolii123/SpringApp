package application.service;

import application.controller.AuthorizationMapHolder;
import application.controller.HasLogout;
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
    @Scheduled(fixedRate = 60000)
    public void checkTheInaction() {
        //TODO вот это выполнить в цикле для всех элементов мапы, мапу получаем через инжектированный сервис
        mapHolder.getAuthorizationMap().entrySet().stream().
                filter(entry -> ((new Date().getTime() - entry.getValue().getDate().getTime())/60000) >= 30).
                forEach(entry -> logout.logout(entry.getKey()));
        mapHolder.getAuthorizationMap();
//        long diff = (date1.getTime() - date2.getTime())/60000;
//        if (diff >= 30) {
//            return true;
//        }
        //TODO добавить удаление из мапы
//        logout.logout(mapHolder.getAuthorizationMap().get());
    }
}
