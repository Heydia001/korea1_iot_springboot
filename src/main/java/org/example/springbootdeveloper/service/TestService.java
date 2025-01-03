package org.example.springbootdeveloper.service;

import org.example.springbootdeveloper.entity.Member;
import org.example.springbootdeveloper.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 비지니스 계층 코드
@Service
public class TestService {
    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
