package com.cz.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);


        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUserPrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic(); // 2. 첫번째 타입 빈 사용
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic(); // 3. 첫번째 만들어진 타입 빈 재사용
        Assertions.assertThat(count2).isEqualTo(1); // 4. 재사용으로 인해 카운트가 2로 증가 (싱글톤에서 재사용으로 의도치 않게 카운트가 2로 증가)

    }


    @Scope("singleton")
    static class ClientBean {

//        @Autowired // 지금 딱 필요한 DL 정도의 기능만 제공
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider; // (두번째 방법) ObjectProvider 의 getObject() 를 호출하면 스프링 컨테이너를 통해 해당 빈을 찾아서 반환 이거를 DL 이라고 함

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider; // (세번째 방법 자바 표준) 타입을 지정하여 스프링 컨테이너를 통해 해당 빈을 찾아서 반환


//        @Autowired
//        private ApplicationContext ac; // (첫번째 방법) 싱글톤에 프로토 타입을 쓸수 없어서 직접 만들어서 주입하는 방법으로 이용하는 방법

        public int logic() {

            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // (세번째 방법 자바 표준 Provider 의 get() 를 호출하면 스프링 컨테이너를 통해 해당 빈을 찾아서 반환)
            prototypeBean.addCount(); // (세번째 방법 자바 표준 Provider 의 get() 를 호출하면 스프링 컨테이너를 통해 해당 빈을 찾아서 반환)
            return prototypeBean.getCount(); // (세번째 방법 자바 표준 Provider 의 get() 를 호출하면 스프링 컨테이너를 통해 해당 빈을 찾아서 반환)

//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // (두번째 방법) 싱글톤 빈과 함께 사용시 getObject() 사용 지금 딱 필요한 DL 정도의 기능만 제공
//            prototypeBean.addCount(); // (두번째 방법) 싱글톤 빈과 함께 사용시 getObject() 사용 지금 딱 필요한 DL 정도의 기능만 제공
//            return prototypeBean.getCount(); // (두번째 방법) 싱글톤 빈과 함께 사용시 getObject() 사용 지금 딱 필요한 DL 정도의 기능만 제공

//            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class); // (첫번째 방법) 싱글톤에 프로토 타입을 쓸수 없어서 직접 만들어서 주입하는 방법으로 이용하는 방법
//            prototypeBean.addCount();
//            return prototypeBean.getCount();

        }
    }



    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("destroy" + this);
        }

    }

}
