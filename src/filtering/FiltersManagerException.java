package filtering;

import filesprocessing.ReaderManagerException;

/**
 * This class represents the exceptions while there is a problem with the filter object.
 */
public class FiltersManagerException extends ReaderManagerException {

    /**
     * Defalt constructor.
     * @param massage the message to print to the user.
     */
    public FiltersManagerException(String massage){

        super(massage);

    }

}
