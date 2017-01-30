package Names;

import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Random;

public class Names {

    private ArrayList<String> firstNames = new ArrayList<>();
    private ArrayList<String> surnameNames = new ArrayList<>();
    private ArrayList<String> entityNames = new ArrayList<>();
    private ArrayList<String> organizationNames = new ArrayList<>();
    private ArrayList<String> degreesNames = new ArrayList<>();
    private ArrayList<String> campiNames = new ArrayList<>();

    Random random = new Random();

    public Names() throws IOException
    {
        //The constructor will initialize both these arrayLists from the files that are in the same folder

        readFile("src/Names/firstname_list.txt", firstNames);
        readFile("src/Names/surname_list.txt", surnameNames);
        readFile("src/Names/entities_list.txt", entityNames);
        readFile("src/Names/organizations_list.txt", organizationNames);
        readFile("src/Names/degrees_list.txt", degreesNames);
        readFile("src/Names/campi_list.txt", campiNames);
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
        int position = random.nextInt(firstNames.size());

        return firstNames.get(position).toLowerCase();
    }

    private String getLastName()
    {
        //Generate a random number to get a random name from the whole list
        int position = random.nextInt(surnameNames.size());

        return surnameNames.get(position).toLowerCase();
    }

    public String getDegree()
    {
        //Generate a random number to get a random name from the whole list
        int position = random.nextInt(degreesNames.size());

        return degreesNames.get(position);
    }

    public String getCampus()
    {
        //Generate a random number to get a random name from the whole list
        int position = random.nextInt(campiNames.size());

        return campiNames.get(position);
    }

    public String getGender()
    {
        int i = random.nextInt(2) + 1;
        if(i == 1)
            return "male";
        else
            return "female";
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




}
