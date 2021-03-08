package com.example.app.util;

import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Password {
    private static final Logger LOGGER = Logger.getLogger(Password.class);

    public static byte[] getPasswordHash(String password) {
        byte[] hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            LOGGER.error("Password: " + e.getMessage());
        }
        return hash;
    }
}
