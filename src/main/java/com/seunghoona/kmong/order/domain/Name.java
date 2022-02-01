package com.seunghoona.kmong.order.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class Name {

    @Column(name = "name")
    private String name;

    public static Name of(String name) {
        if (Objects.isNull(name) || "".equals(name)) {
            throw new IllegalArgumentException("상품명은 필수 입니다.");
        }
        return new Name(name);
    }

    private Name(String name) {
        this.name = name;
    }

}
