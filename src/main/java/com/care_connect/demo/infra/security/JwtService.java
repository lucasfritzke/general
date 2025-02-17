package com.care_connect.demo.infra.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final long jwtExpirationInMs = 2 * 60 * 60 * 1000;




}
