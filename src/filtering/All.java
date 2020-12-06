package filtering;

import java.util.LinkedList;
import java.io.File;

/**
 * This class represents the All filter.
 */
public class All extends FiltersManager {

    /**
     * constructor for the class.
     * @param notOperator an indicator if a reversed filter is needed or not.
     */
    public All(boolean notOperator){
    notFilterOperator = notOperator;
    }

    /**
     * This method filters the files.
     * @param files the files to filter.
     * @return the filtered files.
     */
    public LinkedList<File> doFilter(LinkedList<File> files){
        if(notFilterOperator)
            return null;

        return files;
    }

}
