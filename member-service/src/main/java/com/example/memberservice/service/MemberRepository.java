package com.example.memberservice.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.memberservice.entity.Member;

/** Accès aux adhérents en base MySQL. */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
