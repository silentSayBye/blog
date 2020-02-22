package com.destiny.api.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName IpUtils
 * @Author Administrator
 * @Date 2019/7/2021:10
 * @Version 1.0
 **/

@Slf4j
public class IpUtil {

    private static final String IPPATTERN = "^(?:(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\.){3}(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\b";


    // 获取客户端ip地址，当经过多级代理的情况下，会将代理的ip一起返回，例如192.168.0.11，192.168.0.120
    public static String getclientIP(HttpServletRequest request){
        String ip = "127.0.0.1";
        Assert.notNull(request,"request cannot be null");
        if (request != null){
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
                ip = request.getRemoteAddr();
            }
        }
        return ip;
    }

    // 获取客户端ip地址，当经过多级代理的情况下，会将代理服务器的ip过滤
    public static String getRealclientIP(HttpServletRequest request){
        String ip = "127.0.0.1";
        Assert.notNull(request,"request cannot be null");
        if (request != null){
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)){
                ip = request.getRemoteAddr();
                if ("127.0.0.1".equals(ip) || "0.0.0.0.0.0.0.1".equals(ip)){
                    try {
                        InetAddress inetAddress = InetAddress.getLocalHost();
                        ip = inetAddress.getHostAddress();
                    } catch (UnknownHostException e) {
                        log.info("obtain ip fail,cause {}",e.getCause().getMessage());
                        e.printStackTrace();
                    }
                }
            }
            Assert.notNull(ip,"ip cannot be null");
            ip = ip.split(",")[0];
        }
        return ip;
    }

    public static String getServerIP(){
        Pattern pattern = Pattern.compile(IPPATTERN);
        Enumeration<NetworkInterface> networkInterfaces = null;
        try {
           networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()){
                String ip = inetAddresses.nextElement().getHostAddress();
                Matcher matcher = pattern.matcher(ip);
                if (matcher.matches() && !"127.0.0.1".equals(ip)){
                    return ip;
                }

            }
        }
        return  null;
    }
}
