package com.cz.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 컴포넌트 스캔은 자동으로 등록이다. 수동으로 등록하는 Configuration 은 자동 등록에서 제외한다. 실무에서는 딱히 제외하진 않음. 현재는 이전에 작성한 코드가 있어서 제외힘.
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class))
public class AutoAppConfig {


}
