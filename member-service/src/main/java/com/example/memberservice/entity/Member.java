package com.example.memberservice.entity;

/**
 * Un adhérent de la salle de sport.
 * Exemple JSON : { "id" : 1, "name" : "Alice Martin", "email" : "alice@mail.com", "activeSubscription" : true }
 */
public class Member {

	private long id;
	private String name;
	private String email;
	private boolean activeSubscription;

	public Member() {
	}

	public Member(long id, String name, String email, boolean activeSubscription) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.activeSubscription = activeSubscription;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActiveSubscription() {
		return activeSubscription;
	}

	public void setActiveSubscription(boolean activeSubscription) {
		this.activeSubscription = activeSubscription;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", email=" + email
				+ ", activeSubscription=" + activeSubscription + "]";
	}
}
