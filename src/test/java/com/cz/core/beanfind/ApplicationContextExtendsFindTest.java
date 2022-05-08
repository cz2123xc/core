package com.cz.core.beanfind;

import com.cz.core.AppConfig;
import com.cz.core.discount.DiscountPolicy;
import com.cz.core.discount.FixDiscountPolicy;
import com.cz.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);


    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
//        applicationContext.getBean(DiscountPolicy.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () ->
                applicationContext.getBean(DiscountPolicy.class));
    }


    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = applicationContext.getBean("rateDiscountPolicy", DiscountPolicy.class);

        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 타입으로 조회")
    void findBeanByType() {
        DiscountPolicy rateDiscountPolicy = applicationContext.getBean(RateDiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = applicationContext.getBeansOfType(DiscountPolicy.class);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);

        for (String key : beansOfType.keySet()) {
            System.out.println("key : " + key + ", value : " + beansOfType.get(key));
        }

    }


    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("key : " + key + ", value : " + beansOfType.get(key));
        }

    }




    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }


    }


}
