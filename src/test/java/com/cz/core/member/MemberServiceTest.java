package com.cz.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member member = new Member(1L, "윤지영", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(2L);

        // then
        Assertions.assertThat(findMember).isEqualTo(member);

    }

}
