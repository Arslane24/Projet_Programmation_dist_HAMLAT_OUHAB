package com.example.memberservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.memberservice.entity.Member;

/**
 * Couche service : gère les adhérents de la salle.
 */
@Service
public class MemberService {

	private final List<Member> members = new ArrayList<>();
	private final AtomicLong nextId = new AtomicLong(1);

	public MemberService() {
		members.add(new Member(nextId.getAndIncrement(), "Alice Martin", "alice@mail.com", true));
		members.add(new Member(nextId.getAndIncrement(), "Bob Durand", "bob@mail.com", true));
		members.add(new Member(nextId.getAndIncrement(), "Chloé Petit", "chloe@mail.com", false));
	}

	public List<Member> getMembers() {
		return members;
	}

	public Member getMember(long id) {
		for (Member member : members) {
			if (member.getId() == id) {
				return member;
			}
		}
		throw new MemberNotFoundException(id);
	}

	public Member create(Member member) {
		member.setId(nextId.getAndIncrement());
		members.add(member);
		return member;
	}
}
