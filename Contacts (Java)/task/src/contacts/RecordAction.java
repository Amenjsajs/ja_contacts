package contacts;

import java.util.LinkedHashMap;

public class RecordAction extends AbstractAction {
    protected RecordAction() {
        super("record");
        actionsAllowed = new LinkedHashMap<>() {{
            put("edit", new EditAction());
            put("delete", new DeleteAction());
            put("menu", null);
        }};
    }

    @Override
    protected void run() {
        AbstractAction action;
        do {
            displayMenu();
            lastActionName = input.nextLine();

            action = actionsAllowed.get(lastActionName);

            if (action != null) {
                action.run();
            } else {
                System.out.println();
            }
        } while (action != null);
    }
}
