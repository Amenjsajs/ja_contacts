package contacts;

import java.io.IOException;
import java.util.*;

public class MenuAction extends AbstractAction {
    public MenuAction(String filename) throws IOException, ClassNotFoundException {
        super("menu", filename);
        actionsAllowed = new LinkedHashMap<>() {{
            put("add", new AddAction());
            put("list", new ListAction());
            put("search", new SearchAction());
            put("count", new CountAction());
            put("exit", new ExitAction());
        }};
    }

    @Override
    protected void run() {
        AbstractAction action;
        do {
            recordSelected = null;
            displayMenu();

            lastActionName = input.nextLine();

            action = actionsAllowed.get(lastActionName);
            if (action == null) {
                continue;
            } else if (action.getClass().equals(ExitAction.class)) {
                break;
            }

            action.run();
        } while (true);
    }
}
