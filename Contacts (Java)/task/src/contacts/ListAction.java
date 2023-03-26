package contacts;

import java.util.LinkedHashMap;

public class ListAction extends AbstractAction {
    protected ListAction() {
        super("list");
        actionsAllowed = new LinkedHashMap<>() {{
            put("[number]", new RecordAction());
            put("back", new BackAction());
        }};
    }

    @Override
    protected void run() {
        displayRecords();

        if (contacts.isEmpty()) {
            actionsAllowed.get("back").run();
        } else {
            displayMenu();

            lastActionName = input.nextLine();

            if (lastActionName.equals("back")) {
                actionsAllowed.get("back").run();
            } else {

                try {
                    int i = Integer.parseInt(lastActionName) - 1;
                    AbstractRecord record = selectRecord(i);

                    if (record == null) {
                        System.out.println("Record not found\n");
                    } else {
                        recordSelected = record;
                        System.out.println(record);
                        actionsAllowed.get("[number]").run();
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }
    }

    private void displayRecords() {
        if (contacts.isEmpty()) {
            System.out.println("No records to list");
        } else {
            int i = 0;
            for (AbstractRecord record : contacts) {
                System.out.printf("%d. %s\n", ++i, record.getFullName());
            }
            System.out.println();
        }
    }

    private AbstractRecord selectRecord(int index) {
        try {
            return contacts.get(index);
        } catch (IndexOutOfBoundsException ignored) {
        }
        return null;
    }
}
