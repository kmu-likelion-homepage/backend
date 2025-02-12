package kmu.likelion.homepage.member.service;

import kmu.likelion.homepage.member.entity.Member;
import kmu.likelion.homepage.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    @Value("${spring.jwt.expirationTime}")
    private Long expirationTime;
    @Value("${spring.jwt.refresh-expirationTime}")
    private Long refreshExpirationTime;
    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    public Optional<Member> getMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
