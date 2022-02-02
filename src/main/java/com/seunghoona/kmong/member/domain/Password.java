package com.seunghoona.kmong.member.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class Password {

    public static final int MIN = 6;
    public static final int MAX = 12;
    public static final String PATTERN_PASSWORD = String.format("^[A-Za-z0-9]{%d,%d}$", MIN, MAX);
    private static final Pattern VALID_PASSWORD = Pattern.compile(PATTERN_PASSWORD);

    @Column(name = "password")
    @NotNull
    private String password;

    public Password(String password) {
        if (!VALID_PASSWORD.matcher(password).find()) {
            throw new IllegalArgumentException(String.format("패스워드 길이는 %d 이상 %d 이하이어야합니다.", MIN, MAX));
        }
        this.password = password;
    }

    public Password encode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
        return this;
    }
}
