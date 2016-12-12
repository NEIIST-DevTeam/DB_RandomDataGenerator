package Names;

import javafx.scene.shape.Path;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Random;

public class Names {

    private ArrayList<String> firstNames = new ArrayList<>();
    private ArrayList<String> surnameNames = new ArrayList<>();
    private ArrayList<String> entityNames = new ArrayList<>();
    private ArrayList<String> organizationNames = new ArrayList<>();

    private int oid = 0;

    public Names() throws IOException
    {
        //The constructor will initialize both these arrayLists from the files that are in the same folder

        readFile("src\\Names\\firstname_list.txt", firstNames);
        readFile("src\\Names\\surname_list.txt", surnameNames);
    }

    private void readFile(String fileName, ArrayList<String> names) throws IOException
    {

        //Initialize buffered reader to the file name
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        try
        {
            String line = br.readLine();

            while (line != null)
            {
                names.add(line.toString());
                line = br.readLine();
            }
        }
        finally
        {
            br.close();
        }
    }

    private String getFirstName()
    {
        //Generate a random number to get a random name from the whole list
        Random random = new Random();
        int position = random.nextInt(firstNames.size());

        return firstNames.get(position).toLowerCase();
    }

    private String getLastName()
    {
        //Generate a random number to get a random name from the whole list
        Random random = new Random();
        int position = random.nextInt(surnameNames.size());

        return surnameNames.get(position).toLowerCase();
    }

    public String createEmail()
    {
        //Generate the email to create the in the table Person
        String generatedEmail = getFirstName() + "." + getLastName() + "@tecnico.ulisboa.pt";

        //Normalize the characters, substituting e.g. ã for a, and ç for c
        String normalizedEmail = Normalizer.normalize(generatedEmail, Normalizer.Form.NFD);
        String email = normalizedEmail.replaceAll("[^\\x00-\\x7F]", "");

        return email;
    }

    public void createPerson(int numberOfPeople) {

        //Clean the previous file
        File file = new File("createDatabase.sql");
        file.delete();


        //Using FileWriter will be a lot quicker since we are opening and closing the file
        try(FileWriter fileWriter = new FileWriter("createDatabase.sql", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter print = new PrintWriter(bufferedWriter))
        {
            //Write numberOfPeople times to the file, all of which have different emails
            while(oid < numberOfPeople)
            {
                String query = "INSERT INTO Person (oid, email) VALUES ('" + String.valueOf(++oid) + "','" + createEmail() + "')";
                print.println(query);
            }

        }
        catch (IOException e)
        {
            System.err.println("Error writing on sql file while creating person");
        }

    }
}
