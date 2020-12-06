package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents the Executable filter.
 */
public class Executable extends FiltersManager {

    private static final String YES_PARAM = "YES";
    private static final String NO_PARAM = "NO";
    private static final String INVALID_EXECUTABLE_VALUE = "value does not equal YES or NO in executable " +
            "filter";

    private boolean isExecutable;

    /**
     * constructor for the class.
     * @param notOperator an indicator if a reversed filter is needed or not.
     * @param isExecutable YES / NO string if the file is executable or not.
     */
    public Executable(boolean notOperator, String isExecutable) throws FiltersManagerException{

        if((!isExecutable.equals(YES_PARAM)) && (!isExecutable.equals(NO_PARAM)))
            throw new FiltersManagerException(INVALID_EXECUTABLE_VALUE);
        else
            this.isExecutable = isExecutable.equals(YES_PARAM);

        notFilterOperator = notOperator;
    }

    /**
     * This method filters the files.
     * @param filesToFilter the files to filter.
     * @return the filtered files.
     */
    public LinkedList<File> doFilter(LinkedList<File> filesToFilter){

        LinkedList<File> files = new LinkedList<>();

        if(!notFilterOperator){
            for(File file : filesToFilter){
                if(file.canExecute() == isExecutable)
                    files.add(file);
            }
        } else {
            for(File file : filesToFilter){
                if(!(file.canExecute() == isExecutable))
                    files.add(file);
            }
        }
        return files;
    }

}
