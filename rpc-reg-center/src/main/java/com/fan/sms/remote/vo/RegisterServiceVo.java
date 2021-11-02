package com.fan.sms.remote.vo;

import java.io.Serializable;

public class RegisterServiceVo implements Serializable {

    private String host;  //提供服务者的ip地址
    private int port;    //提供服务者的端口

    public RegisterServiceVo() {
    }

    public RegisterServiceVo(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "RegisterServiceVo{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}