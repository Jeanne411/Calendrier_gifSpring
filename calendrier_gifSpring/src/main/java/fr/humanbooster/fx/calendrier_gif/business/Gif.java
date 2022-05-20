package fr.humanbooster.fx.calendrier_gif.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "gif")
@Data
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Gif {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "date_heure_ajout")
	protected LocalDateTime dateHeureAjout;

	protected String legende;

	@NotNull
	@OneToOne
	@JoinColumn(unique = true)
	protected Jour jour;
	
	@OneToMany(mappedBy = "gif")
	@ToString.Exclude
	private List<Reaction> reactions;

	@ManyToOne
	private Utilisateur utilisateur;

    public Gif() {
		this.dateHeureAjout = LocalDateTime.now();
		this.reactions = new ArrayList<>();

	}

	public Gif(String legende, Jour jour, Utilisateur utilisateur) {
		this();
		this.legende = legende;
		this.jour = jour;
		this.utilisateur = utilisateur;
	}
}