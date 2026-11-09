package com.example.gymbooking.entity;

/**
 * Id du membre envoyé dans le corps de la requête PUT pour réserver.
 * Exemple JSON : { "memberId" : 1 }
 */
public class Reservation {

	private long memberId;

	public Reservation() {
	}

	public Reservation(long memberId) {
		this.memberId = memberId;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Reservation [memberId=" + memberId + "]";
	}
}
