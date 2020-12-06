package filesprocessing;

import java.util.LinkedList;
import filtering.FiltersManager;
import sorting.SorterManager;
import java.io.File;

/**
 * This class represents a command file object.
 */
public class Cmd {

    private SorterManager typeOfSorter;
    private FiltersManager typeOfFilter;
    private LinkedList<String> messagesForUser;

    /**
     * Default constructor.
     */
    public Cmd(){
        messagesForUser = new LinkedList<>();
    }

    /**
     * This method filters and sorts all files in a list and prints the results.
     * @param files the files we need to process.
     */
    public void executeCmd(LinkedList<File> files){
        printMessages();
        LinkedList<File> returnedFiles = typeOfSorter.filesSorter(typeOfFilter.doFilter(files));
        if(returnedFiles != null){
            for(File aFile : returnedFiles)
                System.out.println(aFile.getName());
        }
    }

    /**
     * This method adds warning messages to the command's log.
     * @param msg the message to add.
     */
    public void addMessageForUser(String msg){
        messagesForUser.add(msg);
    }

    /**
     * This method prints all the messages in the command's log.
     */
    private void printMessages(){
        messagesForUser.forEach(System.out::println);
    }

    /**
     * This method sets the type of sorter of the command.
     * @param typeOfSorter the input sorter.
     */
    public void setTypeOfSorter(SorterManager typeOfSorter){
        this.typeOfSorter = typeOfSorter;
    }

    /**
     * This method sets the type of filter of the command.
     * @param typeOfFilter the input filter.
     */
    public void setTypeOfFilter(FiltersManager typeOfFilter){
        this.typeOfFilter = typeOfFilter;
    }
}
