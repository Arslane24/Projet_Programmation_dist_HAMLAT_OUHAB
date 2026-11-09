package com.example.gymbooking.entity;

/**
 * Nom du membre envoyé dans le corps de la requête PUT pour réserver.
 * Exemple JSON : { "memberName" : "Alice" }
 */
public class Reservation {

	private String memberName;

	public Reservation() {
	}

	public Reservation(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "Reservation [memberName=" + memberName + "]";
	}
}
