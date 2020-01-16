package com.kq.docker.util;

import java.net.InetAddress;

public class IPUtil {

    public static String getLocalHost() throws Exception{
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static void main(String[] args) throws Exception{
        System.out.println("ip="+getLocalHost());
    }

}
