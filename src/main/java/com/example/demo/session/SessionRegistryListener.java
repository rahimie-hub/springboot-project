package com.example.demo.session;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Listener to keep track of active HttpSessions.
 */
@WebListener
public class SessionRegistryListener implements HttpSessionListener {

    // Thread-safe collection of active sessions.
    private static final List<HttpSession> sessions = new CopyOnWriteArrayList<>();

    public static List<HttpSession> getActiveSessions() {
        return Collections.unmodifiableList(sessions);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sessions.add(se.getSession());
        System.out.println("Session created: " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sessions.remove(se.getSession());
        System.out.println("Session destroyed: " + se.getSession().getId());
    }
}
