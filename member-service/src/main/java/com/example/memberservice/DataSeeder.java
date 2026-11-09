package com.example.memberservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.memberservice.entity.Member;
import com.example.memberservice.service.MemberRepository;

/** Insère les adhérents de départ au démarrage, uniquement si la base est vide. */
@Component
public class DataSeeder implements CommandLineRunner {

	private final MemberRepository memberRepository;

	public DataSeeder(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public void run(String... args) {
		if (memberRepository.count() == 0) {
			memberRepository.save(new Member("Alice Martin", "alice@mail.com", true));
			memberRepository.save(new Member("Bob Durand", "bob@mail.com", true));
			memberRepository.save(new Member("Chloé Petit", "chloe@mail.com", false));
		}
	}
}
