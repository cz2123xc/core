package com.cz.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
//@Scope(value = "request") // (방법1) 스프링 컨테이너에서 스코프를 관리하는 것이다.
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS) // (방법2) 스프링 컨테이너에서 스코프를 관리하는 것이다. 인터페이스면 INTERFACES 를 준다
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "] " + "[" + requestURL + "] " +message);
    }

    @PostConstruct
    public void init() {
        uuid = java.util.UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] " + " request scope been create: " + "init");
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] " + " request scope been close: " + "close");
    }


}
