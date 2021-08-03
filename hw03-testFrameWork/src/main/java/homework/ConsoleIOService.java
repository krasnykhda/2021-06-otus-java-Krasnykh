package homework;



import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleIOService implements IOService {
    private final PrintStream out;
    private final Scanner in;

    public ConsoleIOService() {
        out = System.out;
        in = new Scanner(System.in);
    }

    @Override
    public void outputStr(String s) {
        out.println(s);
    }
    @Override
    public String readString() {
        return in.nextLine();
    }
}
