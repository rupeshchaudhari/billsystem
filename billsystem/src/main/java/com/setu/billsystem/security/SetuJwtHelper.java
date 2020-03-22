package com.setu.billsystem.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SetuJwtHelper {
	
	@Value("${schemeId}")
    private String schemedId;
	
	@Value("${secret}")
    private String secret;
    
//    public SetuJwtHelper(String secret, String schemedId) {
//        this.schemedId = schemedId;
//        this.secret = secret;
//    }
	
    public void setSchemedId(String schemedId) {
        this.schemedId = schemedId;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }
    public String yieldBearerToken() {
        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        String token = JWT.create()
                .withAudience(this.schemedId)
                .withIssuedAt(new Date())
                .withClaim("jti",  UUID.randomUUID().toString())
                .sign(algorithm);
        return "Bearer " + token;
    }
    public DecodedJWT verifyBearerToken (String bearerToken) throws JWTVerificationException {
        bearerToken = bearerToken.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .acceptLeeway(10)
                .withAudience(this.schemedId)
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(bearerToken);

        return jwt;
        
    }
}