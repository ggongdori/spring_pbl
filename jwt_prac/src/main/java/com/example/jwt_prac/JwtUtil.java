package com.example.jwt_prac;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.Value;
//import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    private final long ACCESS_TOKEN_VALID_TIME = 1000L * 60 * 60; //1시간
    private final long REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24; //24시간

    //Create Token
    public String createAccessToken(String userPk, String role) {
        Claims claims = Jwts.claims().setSubject(userPk);//JWT payload에 저장
        claims.put("role", role); //정보는 키/밸류 쌍으로
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims) //정보 저장
                .setIssuedAt(now)//토큰 발행 시간
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)//jsonwebtoken signaturealgorithm imported
                .compact();
    }

    public String createRefreshToken(String userPk, String role) {
        Claims claims = Jwts.claims().setSubject(userPk);//JWT payload에 저장
        claims.put("role", role); //정보는 키/밸류 쌍으로
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims) //정보 저장
                .setIssuedAt(now)//토큰 발행 시간
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)//jsonwebtoken signaturealgorithm imported
                .compact();
    }

    //jwt 토큰 인증 정보 조회
    public Authentication getAuthentication(String token) {
        String email = getUserPk(token);
        User user = userRepository.findByEmail(email).orElseThrow(
                ()->new RestException(HttpStatus.BAD_REQUEST, "인증되지 않은 유저입니다.")
        );
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities);
    }

    //토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    //request의 header에서 token 값 가져옴
    public String resolveAccessToken(HttpServletRequest request) {
        String token = request.getHeader("access-token");
        return token;
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        String token = null;
        Cookie cookie = WebUtils.getCookie(request, "refresh-token");
        if (cookie != null) {
            token = cookie.getValue();
        }
        return token;
    }

    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String jwtToken) {
        if (refreshTokenService.isExpiredToken(jwtToken)) {
            return false;
        }
        return validateToken(jwtToken);
    }

}
