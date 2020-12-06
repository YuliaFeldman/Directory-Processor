package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents the Between filter.
 */
public class Between extends FiltersManager {

    private static final String MAX_BELOW_MIN = "Max argument is smaller than min argument";
    private static final String NUMBER_IS_NEGATIVE = "One or more of your arguments is negative";
    private static final int CONVERT_TO_KILOBYTES = 1024;

    private double minSize, maxSize;
    /**
     * constructor for the class.
     * @param notOperator an indicator if a reversed filter is needed or not.
     * @param minSize the minimum size threshold.
     */
    public Between(double minSize, double maxSize, boolean notOperator) throws FiltersManagerException{
        if(maxSize < minSize)
            throw new FiltersManagerException(MAX_BELOW_MIN);
        else if(maxSize < 0 || minSize < 0)
            throw new FiltersManagerException(NUMBER_IS_NEGATIVE);
        this.maxSize = maxSize * CONVERT_TO_KILOBYTES;
        this.minSize = minSize * CONVERT_TO_KILOBYTES;
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
            if(file.length() <= maxSize && file.length() >= minSize)
                files.add(file);
            }
        } else {
                for(File file : filesToFilter){
                    if(file.length() > maxSize || file.length() < minSize)
                        files.add(file);
                }
        }
        return files;
    }
}
