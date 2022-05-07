package com.cz.core;

import com.cz.core.discount.DiscountPolicy;
import com.cz.core.discount.FixDiscountPolicy;
import com.cz.core.discount.RateDiscountPolicy;
import com.cz.core.member.MemberRepository;
import com.cz.core.member.MemberService;
import com.cz.core.member.MemberServiceImpl;
import com.cz.core.member.MemoryMemberRepository;
import com.cz.core.order.OrderService;
import com.cz.core.order.OrderServiceImpl;

public class AppConfig {

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    private DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
//        return new FixDiscountPolicy();
    }




    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    public OrderService orderService() {
        return new OrderServiceImpl( memberRepository(), discountPolicy() );
    }


}
