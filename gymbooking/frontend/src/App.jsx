import { useEffect, useRef, useState } from 'react';
import './App.css';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';
const MEMBER_API_URL = import.meta.env.VITE_MEMBER_API_URL || 'http://localhost:8081';

const ICONS = { Yoga: '🧘', Cardio: '🏃', Musculation: '🏋️' };

function initials(name) {
	return name
		.split(' ')
		.map((part) => part[0])
		.join('')
		.slice(0, 2)
		.toUpperCase();
}

function App() {
	const [sessions, setSessions] = useState([]);
	const [members, setMembers] = useState([]);
	const [memberId, setMemberId] = useState(null);
	const [toasts, setToasts] = useState([]);
	const [loading, setLoading] = useState(true);
	const toastId = useRef(0);

	const pushToast = (type, text) => {
		const id = toastId.current++;
		setToasts((t) => [...t, { id, type, text }]);
		setTimeout(() => setToasts((t) => t.filter((toast) => toast.id !== id)), 3500);
	};

	const loadSessions = () => {
		fetch(`${API_URL}/sessions`)
			.then((res) => res.json())
			.then(setSessions)
			.catch(() => pushToast('error', 'Impossible de contacter gymbooking'));
	};

	const loadMembers = () => {
		fetch(`${MEMBER_API_URL}/members`)
			.then((res) => res.json())
			.then((data) => {
				setMembers(data);
				setLoading(false);
			})
			.catch(() => {
				pushToast('error', 'Impossible de contacter member-service');
				setLoading(false);
			});
	};

	useEffect(() => {
		loadSessions();
		loadMembers();
	}, []);

	const book = (sessionCode) => {
		if (!memberId) {
			pushToast('error', 'Choisis un membre avant de réserver');
			return;
		}
		fetch(`${API_URL}/sessions/${sessionCode}?book=true`, {
			method: 'PUT',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({ memberId }),
		})
			.then((res) => {
				if (!res.ok) return res.text().then((text) => Promise.reject(text));
				const member = members.find((m) => m.id === memberId);
				pushToast('success', `${member?.name ?? memberId} a réservé ${sessionCode}`);
				loadSessions();
			})
			.catch((err) => pushToast('error', String(err)));
	};

	const cancel = (sessionCode) => {
		fetch(`${API_URL}/sessions/${sessionCode}?book=false`, { method: 'PUT' })
			.then((res) => {
				if (!res.ok) return res.text().then((text) => Promise.reject(text));
				pushToast('success', `Réservation annulée sur ${sessionCode}`);
				loadSessions();
			})
			.catch((err) => pushToast('error', String(err)));
	};

	const selectedMember = members.find((m) => m.id === memberId);

	return (
		<div className="app">
			<nav className="navbar">
				<div className="brand">
					<span className="brand-icon">🏋️</span>
					<span>GymBooking</span>
				</div>
				<div className="status-pills">
					<span className="pill">member-service</span>
					<span className="pill">gymbooking</span>
				</div>
			</nav>

			<header className="hero">
				<h1>Réserve ta prochaine séance</h1>
				<p>Choisis ton profil, puis réserve une place dans l'une des séances disponibles.</p>
			</header>

			<section className="member-picker">
				<span className="member-picker-label">Membre</span>
				<div className="chips">
					{members.map((m) => (
						<button
							key={m.id}
							className={`chip ${memberId === m.id ? 'chip-selected' : ''} ${!m.activeSubscription ? 'chip-disabled' : ''}`}
							disabled={!m.activeSubscription}
							onClick={() => setMemberId(m.id)}
							title={!m.activeSubscription ? 'Abonnement inactif' : m.email}
						>
							<span className="avatar">{initials(m.name)}</span>
							{m.name}
							{!m.activeSubscription && <span className="chip-flag">inactif</span>}
						</button>
					))}
				</div>
			</section>

			{loading ? (
				<p className="loading">Chargement...</p>
			) : (
				<section className="grid">
					{sessions.map((session) => {
						const full = session.bookedCount >= session.capacity;
						const pct = Math.round((session.bookedCount / session.capacity) * 100);
						return (
							<article key={session.sessionCode} className={`card ${full ? 'full' : ''}`}>
								<div className="card-top">
									<span className="session-icon">{ICONS[session.name] ?? '💪'}</span>
									<span className={`badge ${full ? 'badge-full' : 'badge-open'}`}>
										{full ? 'Complet' : 'Disponible'}
									</span>
								</div>
								<h2>{session.name}</h2>
								<span className="code">{session.sessionCode}</span>

								<div className="places">
									<div className="places-bar">
										<div className="places-fill" style={{ width: `${pct}%` }} />
									</div>
									<span className="places-text">{session.bookedCount} / {session.capacity} places</span>
								</div>

								<div className="actions">
									<button className="btn-book" disabled={full} onClick={() => book(session.sessionCode)}>
										Réserver
									</button>
									<button className="btn-cancel" onClick={() => cancel(session.sessionCode)}>
										Annuler
									</button>
								</div>
							</article>
						);
					})}
				</section>
			)}

			<div className="toasts">
				{toasts.map((t) => (
					<div key={t.id} className={`toast ${t.type}`}>
						{t.text}
					</div>
				))}
			</div>

			{selectedMember && (
				<div className="footer-bar">
					Connecté en tant que <strong>{selectedMember.name}</strong>
				</div>
			)}
		</div>
	);
}

export default App;
