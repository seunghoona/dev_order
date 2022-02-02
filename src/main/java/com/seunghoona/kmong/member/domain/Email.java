package com.seunghoona.kmong.member.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
public class Email {

    private static final Pattern VALID_EMAIL = Pattern.compile("^([\\w\\.\\_\\-])*[a-zA-Z0-9]+([\\w\\.\\_\\-])*([a-zA-Z0-9])+([\\w\\.\\_\\-])+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,8}$");

    @Column(name = "email", unique = true, updatable = false)
    @NotNull
    private String email;

    public Email(String email) {
        validIsNotEmail(email);
        this.email = email;
    }

    private void validIsNotEmail(String email) {
        if (!VALID_EMAIL.matcher(email).find()) {
            throw new IllegalArgumentException("잘못된 이메일 형식입니다.");
        }
    }

    public String getEmail() {
        return email;
    }
}
