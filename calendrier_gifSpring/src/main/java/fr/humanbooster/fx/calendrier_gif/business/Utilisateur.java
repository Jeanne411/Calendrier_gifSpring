package fr.humanbooster.fx.calendrier_gif.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
@ToString
public class Utilisateur {

	private static int NB_POINTS_INITIAL = 500;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nom;

	@NotBlank
	private String prenom;

	@NotBlank
	private String email;

	@Column(name = "mot_de_passe")
	@NotNull
	private String motDePasse;

	@Column(name = "nb_points")
	private int nbPoints;

	@Column(name = "date_heure_inscription")
	private LocalDateTime dateHeureInscription;

	@ManyToOne
	@JoinColumn(name = "theme_id")
	@NotNull
	private Theme theme;

	@OneToMany(mappedBy = "utilisateur")
	@ToString.Exclude
	private List<Gif> gifs;

	@OneToMany(mappedBy = "utilisateur")
	@ToString.Exclude
	private List<Reaction> reactions;

	public Utilisateur() {
		super();
		this.nbPoints = NB_POINTS_INITIAL;
		this.dateHeureInscription = LocalDateTime.now();
		this.gifs = new ArrayList<Gif>();
	}

	public Utilisateur(String nom, String prenom, String email, String motDePasse, Theme theme) {
		this();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.theme = theme;
	}
	
	public Utilisateur(LocalDateTime dateHeureInscription, String nom, String prenom, String email, String motDePasse, Theme theme) {
		this();
		this.dateHeureInscription = dateHeureInscription;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.theme = theme;
	}

}