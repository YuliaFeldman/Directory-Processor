package filtering;

import java.util.LinkedList;
import java.io.File;

/**
 * This class represents the Contains filter.
 */
public class Contains extends FiltersManager {

    private String containedValue;

    /**
     * constructor for the class.
     * @param notOperator an indicator if a reversed filter is needed or not.
     * @param containedValue the contained value we filter with.
     */
    public Contains(boolean notOperator, String containedValue){
        notFilterOperator = notOperator;
        this.containedValue = containedValue;
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
            if(file.getName().contains(containedValue))
                files.add(file);
            }
        } else {
            for(File file : filesToFilter){
                if(!file.getName().contains(containedValue))
                    files.add(file);
            }
        }
        return files;

    }

}
