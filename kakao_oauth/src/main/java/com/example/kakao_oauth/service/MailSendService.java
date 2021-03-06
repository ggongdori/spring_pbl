//package com.example.kakao_oauth.service;
//
//import com.example.kakao_oauth.domain.user.User;
//import com.example.kakao_oauth.exception.CustomException;
//import com.example.kakao_oauth.exception.ErrorCode;
//import com.example.kakao_oauth.util.UserUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Map;
//import java.util.Random;
//
//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//public class MailSendService {
//
//    private final UserUtil userUtil;
//    private final JavaMailSender mailSender;
////    private final MailAuthRepository mailAuthRepository;
//
//    private int size;
//
//    // 이메일 인증 토큰 만료 기간 10분으로 설정
//    private static final Long EMAIL_TOKEN_EXPIRATION = 10L;
//
//    // 인증키 생성
//    private String getKey(int size) {
//        this.size = size;
//        return getAuthCode();
//    }
//
//    // 인증코드 난수 발생
//    private String getAuthCode() {
//        Random random = new Random();
//        StringBuffer buffer = new StringBuffer();
//        int num = 0;
//
//        while (buffer.length() < size) {
//            num = random.nextInt(10);
//            buffer.append(num);
//        }
//
//        return buffer.toString();
//    }
//
//    // 인증메일 보내기
//    public String sendAuthMail(String email) {
//        // 6자리 난수 인증번호 생성
//        String authKey = getKey(6);
//
//        // 인증메일 보내기
//        try {
//            MailUtils sendMail = new MailUtils(mailSender);
//            sendMail.setSubject("[모아모아 서비스 이메일 인증메일 입니다.]");
//            sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
//                    .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
//                    .append("<a href='http://moamoadev.shop/api/users/confirm?email=")
//                    .append(email)
//                    .append("&authKey=")
//                    .append(authKey)
//                    .append("' target='_blank'>이메일 인증 확인</a>")
//                    .toString());
//            sendMail.setFrom("moamoatest1@gmail.com", "관리자");
//            sendMail.setTo(email);
//            sendMail.send();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return authKey;
//    }
//
//    // UserMaliAuth 저장
//    @Transactional
//    public void save(String email, String authKey) {
//        User user = userUtil.findCurrentUser();
//        mailAuthRepository.save(
//                UserMailAuth.builder()
//                        .userId(user.getId())
//                        .mail(email)
//                        .authKey(authKey)
//                        .expirationDate(LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION))
//                        .build());
//    }
//
//    // 이메일 인증 토큰 가져오기
//    public UserMailAuth getAuthToken(Map<String, String> map) {
//        String email = map.get("email");
//        String authKey = map.get("authKey");
//
//        return mailAuthRepository.findByMailAndAuthKeyAndExpirationDateAfterAndExpiredIsFalse(email, authKey, LocalDateTime.now())
//                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_TOKEN));
//    }
//
//}