package com.example.gymbooking.entity;

/**
 * Une séance de sport.
 * Exemple JSON : { "sessionCode" : "YOGA18", "name" : "Yoga", "capacity" : 2, "bookedCount" : 0 }
 */
public class Session {

	private String sessionCode;
	private String name;
	private int capacity;
	private int bookedCount;

	public Session() {
	}

	public Session(String sessionCode, String name, int capacity) {
		this.sessionCode = sessionCode;
		this.name = name;
		this.capacity = capacity;
		this.bookedCount = 0;
	}

	public String getSessionCode() {
		return sessionCode;
	}

	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getBookedCount() {
		return bookedCount;
	}

	public void setBookedCount(int bookedCount) {
		this.bookedCount = bookedCount;
	}

	public boolean isFull() {
		return bookedCount >= capacity;
	}

	@Override
	public String toString() {
		return "Session [sessionCode=" + sessionCode + ", name=" + name + ", capacity=" + capacity
				+ ", bookedCount=" + bookedCount + "]";
	}
}
