package com.cz.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig {
//        @Bean(initMethod = "init", destroyMethod = "destroy") // destroyMethod 는 close() 나 shutdown 을 추론한다.
//        이걸 쓰는 상황은 외부라이브러리에 사용해야 할 때 외에는 스프링에서 공식으로 권장하는 방법인 어노테이션으로 사용
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://www.baidu.com");
            return networkClient;
        }
    }
}
