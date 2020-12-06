package filesprocessing;

/**
 * This class represents exceptions in reading the sorter command.
 */

public class ReaderSortEx extends ReaderManagerException {

    /**
     * Default constructor.
     */
    public ReaderSortEx(){
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param warning  the detail warning message.
     */
    public ReaderSortEx(String warning){
        super(warning);
    }

}
