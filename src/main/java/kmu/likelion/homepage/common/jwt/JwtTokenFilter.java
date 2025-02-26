package kmu.likelion.homepage.common.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kmu.likelion.homepage.common.role.Role;
import kmu.likelion.homepage.member.entity.Member;
import kmu.likelion.homepage.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final MemberService memberService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //인증헤더 받아옴
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        //헤더가 비어있거나 Bearer로 시작하지 않는다면 -> 인증 X
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.split(" ")[1];

        //만료시간이 지났다면 -> 인증 X
        if (JwtTokenUtil.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

        String email = JwtTokenUtil.getEmail(token, secretKey);
        Role role = JwtTokenUtil.getRole(token, secretKey);

        GrantedAuthority authority = new SimpleGrantedAuthority(role.getAuthority());

        Member memberByEmail = memberService.getMemberByEmail(email).orElse(null);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                memberByEmail.getEmail(),null, List.of(authority));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
