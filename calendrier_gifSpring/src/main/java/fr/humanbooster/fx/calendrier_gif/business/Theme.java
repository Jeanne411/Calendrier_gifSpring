package fr.humanbooster.fx.calendrier_gif.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "theme")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Theme {

    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   	@NotBlank
    private String nom;

	public Theme(String nom) {
		this();
		this.nom = nom;
	}
}