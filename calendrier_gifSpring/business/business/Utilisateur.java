
import java.util.*;

/**
 * 
 */
public class Utilisateur {

    /**
     * Default constructor
     */
    public Utilisateur() {
    }

    /**
     * 
     */
    private static int NB_POINTS_INITIAL = 500;

    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String nom;

    /**
     * 
     */
    private String prenom;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String motDePasse;

    /**
     * 
     */
    private int nbPoints;

    /**
     * 
     */
    private LocalDateTime dateHeureInscription;

    /**
     * 
     */
    private Theme theme;

    /**
     * 
     */
    private Set<Gif> gifs;

}