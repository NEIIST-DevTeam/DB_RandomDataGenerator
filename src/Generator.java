
import Names.Names;
import Tables.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Generator {

    private Names names;
    private int oid;
    private HashMap<Integer, String> personTable = new HashMap<>();
    private HashMap<Integer, Student> studentTable = new HashMap<>();


    public Generator(int numberToGenerate) throws IOException
    {
        names = new Names();
        createPerson(numberToGenerate);
    }

    private void createPerson(int numberOfPeople)
    {

        //Write numberOfPeople times to the file, all of which have different emails
        while (oid < numberOfPeople)
        {
            personTable.put(oid, names.createEmail());
            oid++;
        }

    }

    private void createStudent()
    {
        
    }

    public void writeFile()
    {

        //Clean the previous file
        File file = new File("createDatabase.sql");
        if (!file.delete())
            System.err.println("Error deleting the file!");

        //Using FileWriter will be a lot quicker since we are opening and closing the file
        try (FileWriter fileWriter = new FileWriter("createDatabase.sql", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter print = new PrintWriter(bufferedWriter))
        {

            int i = 0;
            while(i < personTable.size())
            {
                //We want to print from oid 1 and on, and HashMap's first index is 0, so ++i and .get(i-1) is needed
                //so that the queries are consistend with the results from the hashmap
                String query = "INSERT INTO Person (oid, email) VALUES ('" + ++i + "','" + personTable.get(i-1) + "')";
                print.println(query);
            }

        }
        catch (IOException e)
        {
            System.err.println("Error writing on sql file while creating person");
        }
    }
}


