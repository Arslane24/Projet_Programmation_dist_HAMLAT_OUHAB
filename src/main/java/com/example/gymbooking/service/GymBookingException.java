package com.example.gymbooking.service;

/** Levée quand l'opération est incompatible avec l'état de la séance (pleine / pas de place prise). */
public class GymBookingException extends RuntimeException {

	public GymBookingException(String message) {
		super(message);
	}
}
