package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents the Writable filter.
 */
public class Writable extends FiltersManager {

    private static final String YES_PARAM = "YES";
    private static final String NO_PARAM = "NO";
    private static final String INVALID_VALUE = "value does not equal YES or NO in writable filter";

    private boolean isWritable;

    /**
     * constructor for the class.
     * @param notOperator an indicator if a reversed filter is needed or not.
     * @param isWritable YES / NO string if the file is writable or not.
     */
    public Writable(boolean notOperator, String isWritable) throws FiltersManagerException{

        if((!isWritable.equals(YES_PARAM)) && (!isWritable.equals(NO_PARAM)))
            throw new FiltersManagerException(INVALID_VALUE);
        else
            this.isWritable = isWritable.equals(YES_PARAM);

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
                if(file.canWrite() == isWritable)
                    files.add(file);
            }
        } else {
            for(File file : filesToFilter){
                if(!(file.canWrite() == isWritable))
                    files.add(file);
            }
        }
        return files;
    }

}
