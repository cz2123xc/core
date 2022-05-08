package com.cz.core.beanfind;

import com.cz.core.AppConfig;
import com.cz.core.member.MemberRepository;
import com.cz.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SameBeenConfig.class);


    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
//        MemberRepository bean = applicationContext.getBean(MemberRepository.class);

        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> {
            applicationContext.getBean(MemberRepository.class);
        });
    }

    @Test
    @DisplayName("이름으로 조회시 같은 이름이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName() {
        MemberRepository memberRepository = applicationContext.getBean("memberRepository1", MemberRepository.class);

        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemoryMemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = applicationContext.getBeansOfType(MemberRepository.class);

        for (String key : beansOfType.keySet()){
            System.out.println("key = " + key + ", value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }



    @Configuration
    static class SameBeenConfig{
        public SameBeenConfig(){
            System.out.println("SameBeenConfig 생성");
        }

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }


    }



}
