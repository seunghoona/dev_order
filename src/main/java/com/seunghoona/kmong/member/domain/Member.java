package com.seunghoona.kmong.member.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(of = "id")
public class Member {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @OneToMany(fetch = LAZY, cascade = {PERSIST, MERGE})
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_member_to_roles"), nullable = false, updatable = false)
    @Builder.Default
    private List<Role> roles = new ArrayList<>();

    public static MemberCreate of(String email, String password) {
        return new MemberCreate(null, new Email(email), new Password(password));
    }

    @AllArgsConstructor
    public static class MemberCreate {
        private Long id;
        private Email email;
        private Password password;
        public Member encode(PasswordEncoder passwordEncoder) {
            return new Member(id, email, password.encode(passwordEncoder), List.of(new Role()));
        }
    }

    public String getPassword() {
        return password.getPassword();
    }

    public String getEmail() {
        return email.getEmail();
    }

    public Set<GrantedAuthority> getRoles() {
        return roles.stream()
                .map(s -> new SimpleGrantedAuthority(s.getRoleStatus().name()))
                .collect(Collectors.toUnmodifiableSet());
    }
}
