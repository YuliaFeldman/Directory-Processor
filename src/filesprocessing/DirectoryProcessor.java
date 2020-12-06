package filesprocessing;

import java.io.*;
import java.util.LinkedList;

/**
 * This class represents the file processor.
 */
public class DirectoryProcessor {

    //number of arguments needed from user
    private static final int NUM_OF_ARGS = 2;

    //_____Error messages:______
    private static final String INVALID_NUM_OF_ARGS = "Error: <Invalid usage - 2 arguments are required> \n";
    private static final String INVALID_SOURCE_DIR = "Error: <Invalid usage - source directory can not" +
            " be accessed> \n";
    private static final String INVALID_COMMANDS_FILE = "Error: <Invalid usage - commands file can not" +
            " be accessed> \n";

    //all files in the source directory
    private LinkedList<File> sourceDirFiles;
    //list of all commands from commands file
    private LinkedList<Cmd> commands;

    /**
     *Constructs a DirectoryProcessor object
     * @param sourceDir source directory supplied by user
     * @param commandsFile commands file supplied by user
     */
    public DirectoryProcessor(File sourceDir, File commandsFile) throws ReaderManagerException{

        //adding files from source directory to a list
        sourceDirFiles = new LinkedList<>();
        File[] subFiles = sourceDir.listFiles();
        for(int i=0; i<subFiles.length; i++){
            if(!subFiles[i].isDirectory())
                sourceDirFiles.add(subFiles[i]);
        }

        //creating a list of command files
        LinkedList<String> allLines = getLines(commandsFile);
        commands = createCmdList(allLines);
    }

    /**
     * reading lines from commands file and adding them to a list
     * @param commandsFile a file with commands
     * @return a list of strings representing the commands
     */
    private LinkedList<String> getLines(File commandsFile){
        LinkedList<String> allLines = new LinkedList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(commandsFile));
            String currentLine = bufferedReader.readLine();
            while(currentLine != null){
                allLines.add(currentLine);
                currentLine = bufferedReader.readLine();
            }
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
        return allLines;
    }

    /**
     * creates a list of commands according to lines from commands file
     * @param allLines a list of lines from commands file
     * @return a list of commands
     */
    private LinkedList<Cmd> createCmdList(LinkedList<String> allLines) throws ReaderManagerException{
        ReaderManager cmdReader = new ReaderManager();
        return cmdReader.readFile(allLines);
    }

    /**
     * The main method of the program, creates a directory processor according to the input given by user
     * and runs it.
     * @param args arguments supplied by user
     */
    public static void main(String[] args){
        try{
            if(args.length != NUM_OF_ARGS)
                throw new DirectoryProcessorEx(INVALID_NUM_OF_ARGS);

            File sourceDir = new File(args[0]);
            File cmdDir = new File(args[1]);

            if (!sourceDir.canRead())
                throw new DirectoryProcessorEx(INVALID_SOURCE_DIR);
            if(!cmdDir.canRead())
                throw new DirectoryProcessorEx(INVALID_COMMANDS_FILE);

            DirectoryProcessor processor = new DirectoryProcessor(sourceDir, cmdDir);

            //running each commands file on all files in source directory
            for(Cmd cmdFile : processor.commands)
                cmdFile.executeCmd(processor.sourceDirFiles);
        }
        catch (DirectoryProcessorEx ex){
            System.err.println(ex.getMessage());
        }
    }
}