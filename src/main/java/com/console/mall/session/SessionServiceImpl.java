package com.console.mall.session;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class SessionServiceImpl implements SessionService {
    private Map<String, String> sessionIdByUsername = new HashMap<>();

    @Override
    public String getSessionIdByUsername(String username) {
        return sessionIdByUsername.get(username);
    }

    @Override
    public HttpSession getSessionById(String sessionId) {
        return SessionRegistry.getSessionById(sessionId);
    }

    @Override
    public void saveSessionIdByUsername(String username, String sessionId) {
        sessionIdByUsername.put(username, sessionId);
    }

    @Override
    public void removeSessionByUsername(String username) {
        sessionIdByUsername.remove(username);
    }
}
