package com.fan.sms.rpc.base;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册服务
 */
@Service
public class RegisterService {
    // 用一个容器存储本地可以提供的服务
    private static final Map<String, Class> serviceCache = new ConcurrentHashMap<>();

    public void regService(String serviceName, Class impl) {
        serviceCache.put(serviceName, impl);
    }

    public Class getLocalService(String serviceName) {
        return serviceCache.get(serviceName);
    }
}
