package fr.humanbooster.fx.calendrier_gif.business;

import java.time.LocalDate;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "jour")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jour {

	@Id
	private LocalDate date;

	@Column(name = "nb_points")
	private int nbPoints;

	@OneToOne(mappedBy = "jour")
	@ToString.Exclude
	private Gif gif;

	private static Random random = new Random();

	public Jour(LocalDate date) {
		this.date = date;
		nbPoints = 20 + random.nextInt(31);
	}

	public Jour(int nbPoints, Gif gif) {
		this();
		this.nbPoints = nbPoints;
		this.gif = gif;
	}

	@Override
	public String toString() {
		return date.getDayOfMonth() + "/" + date.getMonthValue();
	}
}