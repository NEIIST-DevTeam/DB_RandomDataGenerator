
import Names.Names;

import java.io.IOException;


public class Generator {

    private static Names names;

    public static void main(String[] args) throws IOException
    {
        names = new Names();
        names.createPerson(50);
    }

}
