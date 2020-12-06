package filtering;

import java.io.File;
import java.util.LinkedList;

/**
 * This class represents the Prefix filter.
 */
public class Prefix extends FiltersManager{

    private String prefix;

    /**
     * constructor for the class.
     * @param notOperator an indicator if a reversed filter is needed or not.
     * @param prefix the prefix value we filter with.
     */
    public Prefix(boolean notOperator, String prefix){

        notFilterOperator = notOperator;
        this.prefix = prefix;
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
                if(file.getName().startsWith(prefix))
                    files.add(file);
            }
        } else {
            for(File file : filesToFilter){
                if(!(file.getName().startsWith(prefix)))
                    files.add(file);
            }
        }
        return files;
    }
}
