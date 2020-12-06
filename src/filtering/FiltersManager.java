package filtering;

import java.util.LinkedList;
import java.io.File;

/**
 * This abstract class represents a general filter.
 */
public abstract class FiltersManager {

    boolean notFilterOperator;

    /**
     * This method filters the files.
     * @param filesToFilter the files to filter.
     * @return the filtered files.
     */
    public abstract LinkedList<File> doFilter(LinkedList<File> filesToFilter);

}
