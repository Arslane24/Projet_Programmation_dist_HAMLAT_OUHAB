package com.example.memberservice.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.memberservice.entity.Member;
import com.example.memberservice.service.MemberNotFoundException;
import com.example.memberservice.service.MemberService;

/**
 * Couche web : expose les adhérents en REST.
 */
@RestController
@CrossOrigin
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping
	public List<Member> listMembers() {
		return memberService.getMembers();
	}

	@GetMapping("/{id}")
	public Member getMember(@PathVariable("id") long id) {
		return memberService.getMember(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Member createMember(@RequestBody Member member) {
		return memberService.create(member);
	}

	@ExceptionHandler(MemberNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFound(MemberNotFoundException e) {
		return e.getMessage();
	}
}
