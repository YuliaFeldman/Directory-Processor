package sorting;

import java.io.File;
import java.util.LinkedList;
import java.util.Comparator;

/**
 * This class represents a file sorter
 */
public class SorterManager {
    private Comparator<File> typeOfSort;

    /**
     * Constructor
     * @param sortType a Comparator object to use for sorter
     */
    public SorterManager(Comparator<File> sortType){
        this.typeOfSort = sortType;
    }

    /**
     * Method sorts a list of files according to Comparator object
     * @param files a list of all files to sort
     * @return a sorted list of files
     */
    public LinkedList<File> filesSorter(LinkedList<File> files){
        if(files != null){
            files.sort(typeOfSort);
        }
        return files;
    }
}
