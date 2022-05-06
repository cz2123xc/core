package com.cz.core.order;

import com.cz.core.discount.DiscountPolicy;
import com.cz.core.discount.FixDiscountPolicy;
import com.cz.core.discount.RateDiscountPolicy;
import com.cz.core.member.Member;
import com.cz.core.member.MemberRepository;
import com.cz.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}
