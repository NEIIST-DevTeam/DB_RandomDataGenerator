package Names;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Names {

    private ArrayList<String> firstNames = new ArrayList<>();
    private ArrayList<String> surnameNames = new ArrayList<>();
    private ArrayList<String> entityNames = new ArrayList<>();
    private ArrayList<String> organizationNames = new ArrayList<>();

    public Names() throws IOException
    {
        readFile("src\\Names\\firstname_list.txt", firstNames);
        readFile("src\\Names\\surname_list.txt", surnameNames);
    }

    private void readFile(String fileName, ArrayList<String> names) throws IOException
    {

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
}
