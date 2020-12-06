package filesprocessing;
/**
 * This class represents a DirectoryProcessor exception object
 */
public class DirectoryProcessorEx extends Exception{

    /**
     * Default constructor
     */
    public DirectoryProcessorEx(){
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message  the detail message.
     */

    public DirectoryProcessorEx(String message){
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public DirectoryProcessorEx(String message, Throwable cause){
        super(message, cause);
    }
}
