package application.controller;

import application.dao.AutorizationData;

import java.util.Map;

public interface AuthorizationMapHolder {
    Map<String, AutorizationData> getAuthorizationMap();
}
