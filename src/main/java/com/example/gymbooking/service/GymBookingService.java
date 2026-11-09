package com.example.gymbooking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gymbooking.entity.Reservation;
import com.example.gymbooking.entity.Session;

/**
 * Couche service : logique métier de la réservation de séances.
 * Ne connaît pas HTTP, elle manipule uniquement les entités.
 */
@Service
public class GymBookingService {
	private final List<Session> sessions = new ArrayList<>();

	public GymBookingService() {
		sessions.add(new Session("YOGA18", "Yoga", 2));
		sessions.add(new Session("CARDIO19", "Cardio", 1));
		sessions.add(new Session("MUSCU09", "Musculation", 3));
	}

	/** Liste des séances qui ont encore de la place. */
	public List<Session> getAvailableSessions() {
		List<Session> available = new ArrayList<>();
		for (Session session : sessions) {
			if (!session.isFull()) {
				available.add(session);
			}
		}
		return available;
	}

	public Session getSession(String sessionCode) {
		for (Session session : sessions) {
			if (session.getSessionCode().equals(sessionCode)) {
				return session;
			}
		}
		throw new SessionNotFoundException(sessionCode);
	}

	public void book(String sessionCode, Reservation reservation) {
		Session session = getSession(sessionCode);
		if (session.isFull()) {
			throw new GymBookingException("Session " + sessionCode + " is full");
		}
		session.setBookedCount(session.getBookedCount() + 1);
		System.out.println("Booked: " + session + " for " + reservation.getMemberName());
	}

	public void cancel(String sessionCode) {
		Session session = getSession(sessionCode);
		if (session.getBookedCount() == 0) {
			throw new GymBookingException("Session " + sessionCode + " has no booking to cancel");
		}
		session.setBookedCount(session.getBookedCount() - 1);
		System.out.println("Cancelled: " + session);
	}
}
