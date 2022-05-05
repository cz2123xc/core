package com.cz.core;

import com.cz.core.member.Grade;
import com.cz.core.member.Member;
import com.cz.core.member.MemberService;
import com.cz.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "John", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member : " + member.getName());
        System.out.println("findMember : " + findMember.getName());

    }


}
