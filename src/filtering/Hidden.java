package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents the Hidden filter.
 */
public class Hidden extends FiltersManager {

    private static final String YES_PARAM = "YES";
    private static final String NO_PARAM = "NO";
    private static final String INVALID_VALUE = "value does not equal YES or NO in hidden filter";

    private boolean isHidden;

    /**
     * constructor for the class.
     * @param notOperator an indicator if a reversed filter is needed or not.
     * @param isHidden YES / NO string if the file is hidden or not.
     */
    public Hidden(boolean notOperator, String isHidden) throws FiltersManagerException{

        if((!isHidden.equals(YES_PARAM)) && (!isHidden.equals(NO_PARAM)))
            throw new FiltersManagerException(INVALID_VALUE);
        else
            this.isHidden = isHidden.equals(YES_PARAM);

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
                if(file.isHidden() == isHidden)
                    files.add(file);
            }
        } else {
            for(File file : filesToFilter){
                if(!(file.isHidden() == isHidden))
                    files.add(file);
            }
        }
        return files;
    }

}
