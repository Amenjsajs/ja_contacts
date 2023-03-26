package contacts;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String filename = null;
        if (args.length == 1) {
            filename = args[0];
            System.out.printf("open %s\n\n", filename);
        }

        MenuAction menuAction = new MenuAction(filename);
        menuAction.run();
    }
}
