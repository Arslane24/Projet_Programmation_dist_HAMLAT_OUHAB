package com.example.gymbooking.service;

/** Levée quand member-service ne connaît pas ce membre. */
public class MemberNotFoundException extends RuntimeException {

	public MemberNotFoundException(long memberId) {
		super("Member " + memberId + " not found");
	}
}
