package sorting;

import java.io.File;
import java.util.Comparator;

public class TypeCompare implements Comparator<File>{

    private static final int REVERSE_PARAM = -1;
    private boolean reverseOrder;

    /**
     * Constructor
     * @param reverseOrder An indicator which indicates if a reversed order is necessary or not
     */
    public TypeCompare(boolean reverseOrder){
        this.reverseOrder = reverseOrder;
    }

    /**
     *
     * @param file1 first file to compare
     * @param file2 second file to compare
     * @return a negative integer if type of file1 is "smaller" than type of file2, or a positive integer
     * if type of file1 is bigger than type of file2
     */
    public int compare(File file1, File file2){
        String file1Type = getFileType(file1.getAbsolutePath());
        String file2Type = getFileType(file2.getAbsolutePath());
        int comparisonResult = file1Type.compareTo(file2Type);

        //if file types are equal then compare by absolute name
        if(comparisonResult == 0){
            NameCompare nameCompare = new NameCompare(reverseOrder);
            return nameCompare.compare(file1, file2);
        }

        if(reverseOrder)
            return comparisonResult*(REVERSE_PARAM);
        return comparisonResult;
    }

    /**
     * Method returns the type of a given file
     * @param absoluteFilePath the absolute path of a file
     * @return A string representation of the file's type
     */
    private String getFileType(String absoluteFilePath){
        int lastPeriodIndex = absoluteFilePath.lastIndexOf('.');
        int lastSlashIndex = absoluteFilePath.lastIndexOf('/');

        if(lastSlashIndex >= lastPeriodIndex)
            return "";
        return absoluteFilePath.substring(lastPeriodIndex+1);
    }
}
