package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents the SmallerThan filter.
 */
public class SmallerThan extends FiltersManager{

    private static final int CONVERT_TO_KILOBYTES = 1024;

    private static final String NEGATIVE_NUMBER_ERROR = "Please choose a non negative number only";

    private double fileSizeThreshold;

    /**
     * constructor for the class.
     * @param notOperator an indicator if a reversed filter is needed or not.
     * @param sizeLimiter the maximum of a file's size (excluding).
     */
    public SmallerThan(boolean notOperator, double sizeLimiter) throws FiltersManagerException{

        if(fileSizeThreshold < 0)
            throw new FiltersManagerException(NEGATIVE_NUMBER_ERROR);

        fileSizeThreshold = sizeLimiter * CONVERT_TO_KILOBYTES;

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
                if(fileSizeThreshold > file.length())
                    files.add(file);
            }

        } else {
            for (File file : filesToFilter){
                if (fileSizeThreshold <= file.length())
                    files.add(file);
            }
        }

        return files;
    }

}
