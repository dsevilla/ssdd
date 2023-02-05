package es.um.sisdist.backend.dao.models.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserUtils
{
    private static MessageDigest md;
    static
    {
        try
        {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] bytes)
    {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }

    public static String md5pass(String clearpass)
    {
        try
        {
            return bytesToHex(md.digest(clearpass.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e)
        {
            return clearpass;
        }
    }
}
