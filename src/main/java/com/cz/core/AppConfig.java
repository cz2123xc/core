package com.cz.core;

import com.cz.core.discount.FixDiscountPolicy;
import com.cz.core.member.MemberService;
import com.cz.core.member.MemberServiceImpl;
import com.cz.core.member.MemoryMemberRepository;
import com.cz.core.order.OrderService;
import com.cz.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl( new MemoryMemberRepository(), new FixDiscountPolicy() );
    }


}
