package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents the Suffix filter.
 */
public class Suffix extends FiltersManager{

    private String suffix;

    /**
     * constructor for the class.
     * @param notOperator an indicator if a reversed filter is needed or not.
     * @param suffix the suffix value we filter with.
     */
    public Suffix(boolean notOperator, String suffix){

        notFilterOperator = notOperator;
        this.suffix = suffix;
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
                if(file.getName().endsWith(suffix))
                    files.add(file);
            }
        } else {
            for(File file : filesToFilter){
                if(!(file.getName().endsWith(suffix)))
                    files.add(file);
            }
        }
        return files;
    }

}
