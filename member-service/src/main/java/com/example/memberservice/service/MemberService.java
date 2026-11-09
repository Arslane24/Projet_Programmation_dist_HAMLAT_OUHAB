package com.example.memberservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.memberservice.entity.Member;

/**
 * Couche service : gère les adhérents de la salle, persistés en base MySQL.
 */
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public List<Member> getMembers() {
		return memberRepository.findAll();
	}

	public Member getMember(long id) {
		return memberRepository.findById(id)
				.orElseThrow(() -> new MemberNotFoundException(id));
	}

	public Member create(Member member) {
		return memberRepository.save(member);
	}
}
