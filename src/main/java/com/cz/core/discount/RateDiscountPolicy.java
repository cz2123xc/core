package com.cz.core.discount;

import com.cz.core.member.Grade;
import com.cz.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
@Primary // 상속받아 구현한 객체가 여러개일경우 이거를 우선으로 주입한다.
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
               return price * discountPercent / 100;
        } else {
            return 0;
        }
    }


}
