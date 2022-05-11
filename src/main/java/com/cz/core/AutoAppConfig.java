package com.cz.core;

import com.cz.core.member.MemberRepository;
import com.cz.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 컴포넌트 스캔은 자동으로 등록이다. 수동으로 등록하는 Configuration 은 자동 등록에서 제외한다. 실무에서는 딱히 제외하진 않음. 현재는 이전에 작성한 코드가 있어서 제외힘.
@ComponentScan(
//        basePackages = {"com.cz.core"}, // 스캔할 패키지
//        basePackageClasses = {AutoAppConfig.class}, // 스캔할 패키지에 있는 클래스
        // 지정하지 않으면 ComponentScan 의 package 를 기준으로 찾는다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class
        ))
public class AutoAppConfig {

//        @Bean(name = "memoryMemberRepository")
//        MemberRepository memberRepository() {
//                return new MemoryMemberRepository();
//        }


}
