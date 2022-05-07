package com.cz.core;

import com.cz.core.member.Grade;
import com.cz.core.member.Member;
import com.cz.core.member.MemberService;
import com.cz.core.member.MemberServiceImpl;
import com.cz.core.order.Order;
import com.cz.core.order.OrderService;
import com.cz.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "John", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "상품A", 10000);
        System.out.println("order = " + order.toString());
        System.out.println("order.calculatePrice = " + order.calculatePrice());

    }


}
