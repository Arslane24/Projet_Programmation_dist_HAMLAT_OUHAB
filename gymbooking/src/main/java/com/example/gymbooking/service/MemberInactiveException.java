package com.example.gymbooking.service;

/** Levée quand le membre n'a pas d'abonnement actif. */
public class MemberInactiveException extends RuntimeException {

	public MemberInactiveException(long memberId) {
		super("Member " + memberId + " has no active subscription");
	}
}
