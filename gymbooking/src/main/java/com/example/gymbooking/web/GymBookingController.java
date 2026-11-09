package com.example.gymbooking.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.gymbooking.entity.Reservation;
import com.example.gymbooking.entity.Session;
import com.example.gymbooking.service.GymBookingException;
import com.example.gymbooking.service.GymBookingService;
import com.example.gymbooking.service.MemberInactiveException;
import com.example.gymbooking.service.MemberNotFoundException;
import com.example.gymbooking.service.SessionNotFoundException;

/**
 * Couche web : expose les services REST et traduit les erreurs métier en codes HTTP.
 */
@RestController
@CrossOrigin
public class GymBookingController {
	private final GymBookingService gymBookingService;

	public GymBookingController(GymBookingService gymBookingService) {
		this.gymBookingService = gymBookingService;
	}

	/** GET /sessions : liste des séances qui ont encore de la place */
	@GetMapping("/sessions")
	@ResponseStatus(HttpStatus.OK)
	public List<Session> listOfSessions() {
		return gymBookingService.getAvailableSessions();
	}

	/** GET /sessions/{sessionCode} : caractéristiques d'une séance */
	@GetMapping("/sessions/{sessionCode}")
	@ResponseStatus(HttpStatus.OK)
	public Session aSession(@PathVariable("sessionCode") String sessionCode) {
		return gymBookingService.getSession(sessionCode);
	}

	/**
	 * PUT /sessions/{sessionCode}?book=true  + body {"memberName":"..."} : réserver
	 * PUT /sessions/{sessionCode}?book=false (sans body)                 : annuler
	 */
	@PutMapping("/sessions/{sessionCode}")
	@ResponseStatus(HttpStatus.OK)
	public void bookOrCancel(
			@PathVariable("sessionCode") String sessionCode,
			@RequestParam(value = "book", required = true) boolean book,
			@RequestBody(required = false) Reservation reservation) {

		if (book) {
			if (reservation == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member id is required in the body");
			}
			gymBookingService.book(sessionCode, reservation);
		} else {
			gymBookingService.cancel(sessionCode);
		}
	}

	@ExceptionHandler({ SessionNotFoundException.class, MemberNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFound(RuntimeException e) {
		return e.getMessage();
	}

	@ExceptionHandler({ GymBookingException.class, MemberInactiveException.class })
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleConflict(RuntimeException e) {
		return e.getMessage();
	}
}
