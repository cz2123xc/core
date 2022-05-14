package com.cz.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void protoTypeBeanFind() { // 프로토타입으로 만든 객체는 다르다는것을 증명
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class); // 이걸로 등록하면 자동으로 컴포넌트 스캔한거처럼 등록됨
        System.out.println("find prototype bean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototype bean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        
        prototypeBean1.destroy(); // 만약 종료될때 실행되는 메소드가 필요하면 이런 식으로 호출
        prototypeBean2.destroy(); // 만약 종료될때 실행되는 메소드가 필요하면 이런 식으로 호출
        

        ac.close(); // 프로토타입은 필요 없으므로 호출되지 않는다
    }

    @Scope("prototype") // 프로토타입은 조회할때 생성되고, 초기화 메서드도 실행됨
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("prototype bean init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototype bean destroy");
        }

    }
}
