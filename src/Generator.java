
import Names.Names;
import Tables.Associate;
import Tables.Entity;
import Tables.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Generator {

    private Names names;
    private int oid;
    private HashMap<Integer, String> personTable = new HashMap<>();
    private HashMap<Integer, Student> studentTable = new HashMap<>();
    private HashMap<Integer, Entity> entityTable = new HashMap<>();
    private HashMap<Integer, Associate> associateTable = new HashMap<>();

    Random random = new Random();


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

            String email = names.createEmail();
            personTable.put(oid, email);


            int chance = randomGenerator();

            //Let's say there's a 80% chance of the person created being a student
            if(chance < 80)
            {
                String ist_id = "ist" + String.valueOf(random.nextInt(999999));
                Student student = new Student(ist_id, email, names.getDegree(), names.getCampus());
                studentTable.put(oid, student);
            }

            //Let's say there's a 20% chance of the person being an associate (all associates will be students also in this case)
            if(chance < 20)
            {
                String day = String.valueOf(random.nextInt(30) + 1);       //day has values between 1 and 30
                String month = String.valueOf(random.nextInt(12) + 1);     //month has values between 1 and 12
                String year = String.valueOf(random.nextInt(15) + 1985); //year has values between 1985 and 2000

                String date = day + "/" + month + "/" + year;
                Associate associate = new Associate(date, names.getGender());
                associateTable.put(oid, associate);

            }
            oid++;
        }

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

                if(studentTable.containsKey(i))
                {
                    Student student = studentTable.get(i);
                    String studentQuery = "INSERT INTO STUDENT (person_oid, ist_id, ist_email, degree, campus) VALUES ('"
                            + i + "','" + student.getIst_id() + "','" + student.getIst_email() + "','" + student.getDegree()
                            + "','" + student.getCampus() + "')";
                    print.println(studentQuery);
                }

                if(associateTable.containsKey(i))
                {
                    Associate associate = associateTable.get(i);
                    String associateQuery = "INSERT INTO Associate (person_oid, birthdate, gender) VALUES ('"
                            + oid + "','" + associate.getDate() + "','" + associate.getGender() + "')";
                    print.println(associateQuery);
                }

            }

        }
        catch (IOException e)
        {
            System.err.println("Error writing on sql file while creating person");
        }
    }

    private int randomGenerator()
    {
        return random.nextInt(100);
    }
}


