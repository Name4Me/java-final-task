package com.example.app.util;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    void getPasswordHash() {
        assertEquals("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", Hex.encodeHexString(Password.getPasswordHash("test")));
    }
}