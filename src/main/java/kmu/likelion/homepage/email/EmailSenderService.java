package kmu.likelion.homepage.email;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@Primary
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;

    public void sendVerificationCode(String to, String code) {
        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy. MM. dd.");

        LocalTime nowTime = LocalTime.now();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("KMU 멋쟁이사자처럼 이메일 인증");
        message.setText("""
                안녕하세요, 멋쟁이사자처럼대학 at 국민대입니다..
                요청 일시: %s
                
                위 환경에서 본 이메일 주소로 이메일 인증을 시도했습니다.
                본인이 맞다면 계속 진행해주세요.
                
                %s
                
                위 인증 번호의 유효 기간은 요청 시점으로부터 약 5분입니다.
                감사합니다.
                """
                .formatted(nowDate.format(dateTimeFormatter) + formatTime(nowTime),
                        code));
        mailSender.send(message);
    }

    public String formatTime(LocalTime now) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(":mm:ss");
        if (now.getHour() >= 12){
            String resultTime = now.getHour() % 12 + "";
            return " 오후 " + resultTime + now.format(timeFormatter);
        }
        else{
            return " 오전 " + now.getHour() + now.format(timeFormatter);
        }
    }
}
