package ru.urfu.timus.task36_2002;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int queryCount = Integer.parseInt(input.readLine());
        Map<String, String> passwordByUsername = new HashMap<>();
        Map<String, Boolean> isLoggedInByUsername = new HashMap<>();
        StringBuilder protocolLog = new StringBuilder();
        for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
            StringTokenizer line = new StringTokenizer(input.readLine());
            String command = line.nextToken();
            if ("register".equals(command)) {
                appendRegisterOutcome(line, passwordByUsername, isLoggedInByUsername, protocolLog);
            } else if ("login".equals(command)) {
                appendLoginOutcome(line, passwordByUsername, isLoggedInByUsername, protocolLog);
            } else {
                appendLogoutOutcome(line, passwordByUsername, isLoggedInByUsername, protocolLog);
            }
        }
        System.out.print(protocolLog);
    }

    static void appendRegisterOutcome(
            StringTokenizer line,
            Map<String, String> passwordByUsername,
            Map<String, Boolean> isLoggedInByUsername,
            StringBuilder protocolLog) {
        String username = line.nextToken();
        String password = line.nextToken();
        if (passwordByUsername.containsKey(username)) {
            protocolLog.append("fail: user already exists\n");
            return;
        }
        passwordByUsername.put(username, password);
        isLoggedInByUsername.put(username, false);
        protocolLog.append("success: new user added\n");
    }

    static void appendLoginOutcome(
            StringTokenizer line,
            Map<String, String> passwordByUsername,
            Map<String, Boolean> isLoggedInByUsername,
            StringBuilder protocolLog) {
        String username = line.nextToken();
        String password = line.nextToken();
        if (!passwordByUsername.containsKey(username)) {
            protocolLog.append("fail: no such user\n");
            return;
        }
        if (!passwordByUsername.get(username).equals(password)) {
            protocolLog.append("fail: incorrect password\n");
            return;
        }
        if (Boolean.TRUE.equals(isLoggedInByUsername.get(username))) {
            protocolLog.append("fail: already logged in\n");
            return;
        }
        isLoggedInByUsername.put(username, true);
        protocolLog.append("success: user logged in\n");
    }

    static void appendLogoutOutcome(
            StringTokenizer line,
            Map<String, String> passwordByUsername,
            Map<String, Boolean> isLoggedInByUsername,
            StringBuilder protocolLog) {
        String username = line.nextToken();
        if (!passwordByUsername.containsKey(username)) {
            protocolLog.append("fail: no such user\n");
            return;
        }
        if (!Boolean.TRUE.equals(isLoggedInByUsername.get(username))) {
            protocolLog.append("fail: already logged out\n");
            return;
        }
        isLoggedInByUsername.put(username, false);
        protocolLog.append("success: user logged out\n");
    }
}
