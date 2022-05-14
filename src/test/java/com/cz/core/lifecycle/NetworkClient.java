package com.cz.core.lifecycle;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient /*implements InitializingBean, DisposableBean*/ {

    // InitializingBean, DisposableBean 현재는 잘 사용하지 않음 다른 좋은 방법들이 많음

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("Connect: " + url);
    }

    public void call(String message) {
        System.out.println("Call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("Disconnect: " + url);
    }


    @PostConstruct
    public void init() { // 의존관계 설정이 끝나면 호출되는 부분(함수이름 그대로 작동하는 스프링 부품)
        System.out.println("afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void destroy() { // 빈이 종료될 때 호출
        System.out.println("destroy");
        disconnect();
    }


//    @Override
//    public void afterPropertiesSet() throws Exception { // 의존관계 설정이 끝나면 호출되는 부분(함수이름 그대로 작동하는 스프링 부품)
//        System.out.println("afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    @Override
//    public void destroy() throws Exception { // 빈이 종료될 때 호출
//        System.out.println("destroy");
//        disconnect();
//    }
}
