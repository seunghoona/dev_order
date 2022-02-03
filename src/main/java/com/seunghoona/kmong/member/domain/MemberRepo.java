package com.seunghoona.kmong.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepo extends JpaRepository<Member, Long> {

    @Query(value = "select m from Member m join fetch m.roles")
    Optional<Member> findByEmail(Email Email);
}
