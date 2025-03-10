package kmu.likelion.homepage.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 적용할 URL 패턴
                .allowedOrigins("*") // 허용된 Origin: 프론트엔드 주소
                .allowedMethods("*") // 허용된 Http Methods
                .allowedHeaders("Authorization", "Content-Type") // 허용된 헤더
                .exposedHeaders("*") // 노출할 헤더값
                .allowCredentials(false) // 쿠키 인증 요청
                .maxAge(3600); // pre-flight request 캐싱 시간
    }
}
