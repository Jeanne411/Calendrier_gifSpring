package fr.humanbooster.fx.calendrier_gif.business;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reaction")
@Data
@NoArgsConstructor
public class Reaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date_heure")
	private LocalDateTime dateHeure;

	@ManyToOne
	private Gif gif;

	@ManyToOne
	private Utilisateur utilisateur;

	@ManyToOne
	private Emotion emotion;

	public Reaction(Gif gif, Utilisateur utilisateur, Emotion emotion) {
		this.gif = gif;
		this.utilisateur = utilisateur;
		this.emotion = emotion;
		gif.getReactions().add(this);
	}

	@Override
	public String toString() {
		return "Reaction [dateHeure=" + dateHeure + ", gif=" + gif.getId() + ", utilisateur=" + utilisateur.getPrenom()
				+ ", emotion=" + emotion.getNom() + "]";
	}
}