package com.cz.core.autowired;

import com.cz.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }
    static class TestBean {

        @Autowired(required = false) // required 설정시 주입되는 의존관계 객체가 없을 경우 호출 자체가 되지 않는다.
        public void setNoBean(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setBoBean2(@Nullable Member noBean2){ // @Nullable 설정시 null 이 주입될 수 있다. 호출은 된다.
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){ // Optional 은 값이 없으면 Optional.empty 가 주입이 된다.
            System.out.println("noBean3 = " + noBean3);
        }


    }

}
