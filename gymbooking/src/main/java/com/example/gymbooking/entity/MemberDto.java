package com.example.gymbooking.entity;

/**
 * Copie partielle d'un Member, telle que renvoyée par member-service.
 */
public class MemberDto {

	private long id;
	private String name;
	private String email;
	private boolean activeSubscription;

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
}
