package com.commandoby.sonyShop.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Repository;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Repository
public class JwtTokenRepository implements CsrfTokenRepository {

    private String secret;

    public JwtTokenRepository() {
        this.secret = "springrest";
    }

	public String getSecret() {
		return secret;
	}

	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
        String id = UUID.randomUUID().toString().replace("-", "");
        Date now = new Date();
        Date exp = Date.from(LocalDateTime.now().plusMinutes(30)
                .atZone(ZoneId.systemDefault()).toInstant());

        String token = "";
        try {
            token = Jwts.builder()
                    .setId(id)
                    .setIssuedAt(now)
                    .setNotBefore(now)
                    .setExpiration(exp)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
        } catch (JwtException e) {
            e.printStackTrace();
        }
        return new DefaultCsrfToken("x-csrf-token", "_csrf", token);
	}

	@Override
	public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        if (Objects.nonNull(token)) {
            if (!response.getHeaderNames().contains("*"))
                response.addHeader("*", token.getHeaderName());

            if (response.getHeaderNames().contains(token.getHeaderName()))
                response.setHeader(token.getHeaderName(), token.getToken());
            else
                response.addHeader(token.getHeaderName(), token.getToken());
        }
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
	}
	
	public void clearToken(HttpServletResponse response) {
        if (response.getHeaderNames().contains("x-csrf-token"))
            response.setHeader("x-csrf-token", "");
    }

}
