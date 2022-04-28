package com.example.jpabook.jpashop.service;

import com.example.jpabook.jpashop.domain.Member;
import com.example.jpabook.jpashop.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true) //findMembers, findOne 읽기만 -> 약간의 성능 상승
@Service
public class MemberService {


    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }


    private void validateDuplicateMember(Member member) {
        List<Member> findMember = memberRepository.findByName(member.getName());
        if (!findMember.isEmpty()) {
            throw new IllegalStateException("이미 가입되어 있는 회원입니다.");
        }
    }


    //회원 전체 조회

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {

        return memberRepository.findOne(memberId);
    }
}
