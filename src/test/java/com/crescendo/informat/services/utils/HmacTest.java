package com.crescendo.informat.services.utils;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Gilles on 1/06/2017.
 */
public class HmacTest {



    private static final String CORRECT_HASH = "HmacSHA256";
    private static final int CORRECT_HASHLENGTH = 64;

    @Test
    public void hmacTest() throws IOException {
        String message = "test";
        InformatKey informatKey = new InformatKey();
        String privateKey = informatKey.PRIVATE_KEY();

        String hash = Hmac.getHash(message, privateKey, CORRECT_HASH );

        assertEquals(CORRECT_HASHLENGTH, hash.length());
    }

    @Test
    public void hmacWithWrongAlgorithm() throws IOException {
        String message = "test";
        InformatKey informatKey = new InformatKey();
        String privateKey = informatKey.PRIVATE_KEY();

        String hash = Hmac.getHash(message, privateKey, "HmacSHA512" );

        assertNotEquals(CORRECT_HASHLENGTH, hash.length());
    }

    @Test
    public void hmacSameMessageTest() throws IOException {
        String message = "test";
        InformatKey informatKey = new InformatKey();
        String privateKey = informatKey.PRIVATE_KEY();

        String hash = Hmac.getHash(message, privateKey , CORRECT_HASH );
        String hash2 = Hmac.getHash(message, privateKey , CORRECT_HASH );

        assertEquals(hash, hash2);
    }

    @Test
    public void hmacDifferentMessaeTest() throws IOException {
        String message = "test";
        String message2 = "GeenTest";
        InformatKey informatKey = new InformatKey();
        String privateKey = informatKey.PRIVATE_KEY();

        String hash = Hmac.getHash(message, privateKey , CORRECT_HASH );
        String hash2 = Hmac.getHash(message2, privateKey , CORRECT_HASH );

        assertNotEquals(hash, hash2);
    }
}