import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException
    {
        Generator generator = new Generator(Integer.parseInt(args[0]));
        generator.writeFile();
    }
}
