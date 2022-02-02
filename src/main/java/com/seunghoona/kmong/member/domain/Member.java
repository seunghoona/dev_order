package com.seunghoona.kmong.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    public static MemberCreate of(String email, String password) {
        return new MemberCreate(null, new Email(email), new Password(password));
    }

    @AllArgsConstructor
    public static class MemberCreate {
        private Long id;
        private Email email;
        private Password password;
        public Member encode(PasswordEncoder passwordEncoder) {
            return new Member(id, email, password.encode(passwordEncoder));
        }
    }

    public String getEmail() {
        return email.getEmail();
    }
}
