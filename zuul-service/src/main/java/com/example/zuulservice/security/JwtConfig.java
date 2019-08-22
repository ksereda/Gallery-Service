package com.example.zuulservice.security;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * Spring doesn't inject/autowire (inject noting) to "static" fields.
 *
 * So you have two alternatives:
 * 1) (the better one) make the field non static
 * 2) (the ugly hack) add an none static setter which writes in the static field, and add the @Value annotation to the setter.
 */

@Getter
@ToString
public class JwtConfig {

   @Value("${security.jwt.uri:/auth/**}")
   private String Uri;

   @Value("${security.jwt.header:Authorization}")
   private String header;

   @Value("${security.jwt.prefix:Bearer }")
   private String prefix;

   @Value("${security.jwt.expiration:#{24*60*60}}")
   private int expiration;

   @Value("${security.jwt.secret:JwtSecretKey}")
   private String secret;

   public String getUri() {
       return Uri;
   }

   public String getHeader() {
       return header;
   }

   public String getPrefix() {
       return prefix;
   }

   public int getExpiration() {
       return expiration;
   }

   public String getSecret() {
       return secret;
   }

}
