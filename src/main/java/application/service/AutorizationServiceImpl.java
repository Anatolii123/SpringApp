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
        mapHolder.getAuthorizationMap().entrySet().stream().
                filter(entry -> ((new Date().getTime() - entry.getValue().getDate().getTime())/60000) >= 30).
                forEach(entry -> logout.logout(entry.getKey()));
    }
}
