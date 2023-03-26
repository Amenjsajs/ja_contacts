package contacts;

import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public abstract class AbstractAction {
    protected Scanner input = new Scanner(System.in);
    protected String name;
    protected static List<AbstractRecord> contacts;
    protected Map<String, AbstractAction> actionsAllowed;
    protected StringBuilder menu;
    protected String lastActionName;
    protected static String filename = null;
    protected static AbstractRecord recordSelected;

    AbstractAction(String name) {
        this.name = name;
    }

    AbstractAction(String name, String filename) throws IOException, ClassNotFoundException {
        this(name);
        AbstractAction.filename = filename;
        contacts = new LinkedList<>();

        if (filename != null) {
            if (Files.exists(Paths.get(filename))) {
                try {
                    contacts = (List<AbstractRecord>) Utils.deserialize(filename);
                } catch (EOFException e) {
                    contacts = new LinkedList<>();
                }
            } else {
                Utils.serialize(contacts, filename);
            }
        }
    }

    protected abstract void run();

    protected void displayMenu() {
        if (menu == null) {
            menu = new StringBuilder("[").append(name).append("] Enter action ");

            final Object[] keys = actionsAllowed.keySet().toArray();
            menu.append("(").append(keys[0]);
            for (int i = 1, len = keys.length; i < len; i++) {
                menu.append(", ").append(keys[i]);
            }
            menu.append("): ");
        }
        System.out.print(menu);
    }

    protected int save() {
        if (filename != null) {
            try {
                Utils.serialize(contacts, filename);
                return 1;
            } catch (IOException e) {
                return -1;
            }
        }
        return 0;
    }
}
