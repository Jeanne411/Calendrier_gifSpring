
import java.util.*;

/**
 * 
 */
public abstract class Gif {

    /**
     * Default constructor
     */
    public Gif() {
    }

    /**
     * 
     */
    protected Long id;

    /**
     * 
     */
    protected LocalDateTime dateHeureAjout;

    /**
     * 
     */
    protected String legende;

    /**
     * 
     */
    private Set<Reaction> reactions;


    /**
     * 
     */
    protected Jour jour;

}