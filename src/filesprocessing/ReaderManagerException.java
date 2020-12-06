package filesprocessing;

/**
 * This class represents the Exceptions in reading a cmd file.
 */
public class ReaderManagerException extends DirectoryProcessorEx {

    /**
     * Default constructor
     */
    public ReaderManagerException(){
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param warning  the detail warning message.
     */
    public ReaderManagerException(String warning){
        super(warning);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param warning the detail warning message.
     * @param explanation the cause of exception.
     */
    public ReaderManagerException(Throwable explanation, String warning){
        super(warning, explanation);
    }

}
