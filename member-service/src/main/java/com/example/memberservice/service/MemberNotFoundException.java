package com.example.memberservice.service;

/** Levée quand aucun membre ne correspond à l'id demandé. */
public class MemberNotFoundException extends RuntimeException {

	public MemberNotFoundException(long id) {
		super("Member " + id + " not found");
	}
}
