package com.cz.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// Bean 등록 따로 하지 않고 컴포넌트 스캔을 쓴다.
// Autowired 해줄려면 컴포넌트 등록이 되어 있어야 한다.
@Component
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }



}
