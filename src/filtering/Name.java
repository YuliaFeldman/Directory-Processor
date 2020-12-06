package filtering;

import java.util.LinkedList;
import java.io.File;

/**
 * This class represents the Name filter.
 */
public class Name extends FiltersManager {

    private String name;

    /**
     * constructor for the class.
     * @param notOperator an indicator if a reversed filter is needed or not.
     * @param name the file's name we filter with.
     */
    public Name(boolean notOperator, String name) {
        notFilterOperator = notOperator;
        this.name = name;
    }

    /**
     * This method filters the files.
     * @param filesToFilter the files to filter.
     * @return the filtered files.
     */
    public LinkedList<File> doFilter(LinkedList<File> filesToFilter) {

        LinkedList<File> files = new LinkedList<>();

        if (!notFilterOperator) {
            for (File file : filesToFilter) {
                if(file.getName().equals(name))
                    files.add(file);
            }
        } else {
            for(File file : filesToFilter){
                if(!(file.getName().equals(name)))
                    files.add(file);
            }
        }
        return files;
    }
}
