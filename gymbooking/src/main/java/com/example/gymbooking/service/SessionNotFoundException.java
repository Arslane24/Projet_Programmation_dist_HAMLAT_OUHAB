package com.example.gymbooking.service;

/** Levée quand aucune séance ne correspond au code demandé. */
public class SessionNotFoundException extends RuntimeException {

	public SessionNotFoundException(String sessionCode) {
		super("Session " + sessionCode + " not found");
	}
}
