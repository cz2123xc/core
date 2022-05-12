package com.cz.core.order;

import com.cz.core.discount.DiscountPolicy;
import com.cz.core.discount.FixDiscountPolicy;
import com.cz.core.discount.RateDiscountPolicy;
import com.cz.core.member.Member;
import com.cz.core.member.MemberRepository;
import com.cz.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final 붙은 생성자를 자동으로 생성해준다.
public class OrderServiceImpl implements OrderService {

//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    private final MemberRepository memberRepository; // final 이면 생성자에서 초기화해준다. 생성자에서 값이 설정되지 않으면 오류가 떠서 오류를 캐치 해준다.
    private final DiscountPolicy discountPolicy;

//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;


//        @Autowired  // setter injection 이용 생성할때 자동 주입되지 않기 때문에 자동주입이 안됨 Autowired 역할은 MemberRepository 가 무엇인지 알려주는 역할
//        public void setMemberRepository(MemberRepository memberRepository){
//            System.out.println("setMemberRepository()");
//            this.memberRepository = memberRepository;
//        }
//
//        @Autowired
//        public void setDiscountPolicy(DiscountPolicy discountPolicy){
//            System.out.println("setDiscountPolicy()");
//            this.discountPolicy = discountPolicy;
//        }

//    @Autowired (required = false) // 주입할 대상이 없어도 동작 할 수 있도록. 필수값이 아니라는 뜻
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository: " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//    @Autowired // 변경 가능성이 있는 경우에는 생성자를 통한 주입이 아니라 setter 를 통한 주입이 더 좋다.
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy :" + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

//    @Autowired 일반 생성자를 통한 주입 (한번에 여러 필드를 주입 받을 수 있음, 일반적으로 잘 사용하지 않음)
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

//    @Autowired
//    private DiscountPolicy rateDiscountPolicy;

//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) { // bean 이름 여러개일때 이름을 지정 해준다
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + rateDiscountPolicy);
//        this.memberRepository = memberRepository;
//        this.discountPolicy = rateDiscountPolicy;
//    }

//    @Autowired // 생성자로 생성한 객체가 2개 이상일때 @Qualifier 이용해서 주입할 대상을 직접 지정해 줄 수 있다.
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) { // bean 이름 여러개일때 이름을 지정 해준다
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


    @Autowired // 생성자로 생성한 객체가 2개 이상일때 @Qualifier 이용해서 주입할 대상을 직접 지정해 줄 수 있다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { // bean 이름 여러개일때 이름을 지정 해준다
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }



}
