package contacts;

import java.util.*;
import java.util.regex.Pattern;

public class SearchAction extends AbstractAction {
    protected SearchAction() {
        super("search");
        actionsAllowed = new LinkedHashMap<>() {{
            put("[number]", new RecordAction());
            put("back", new BackAction());
        }};

        actionsAllowed.put("again", new AgainAction());
    }

    @Override
    protected void run() {
        if (contacts.isEmpty()) {
            System.out.println("No records to search\n");
            return;
        }
        System.out.print("Enter search query: ");
        String query = ".*" + input.nextLine() + ".*";

        final List<AbstractRecord> search = search(query);
        int count = search.size();
        System.out.printf("Found %d result%s\n", count, count > 1 ? "s" : "");

        if (count > 0) {
            System.out.printf("1. %s\n", search.get(0).getFullName());
            for (int i = 1; i < search.size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), search.get(i).getFullName());
            }
            System.out.println();
        }

        AbstractAction action = null;
        do {
            displayMenu();

            lastActionName = input.nextLine();

            if (lastActionName.equals("back") || lastActionName.equals("again")) {
                action = actionsAllowed.get(lastActionName);
                action.run();
                break;
            } else {
                try {
                    int i = Integer.parseInt(lastActionName) - 1;
                    recordSelected = search.get(i);

                    System.out.println(recordSelected);
                    action = actionsAllowed.get("[number]");
                    action.run();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid action \n");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid index \n");
                }
            }

            if (action != null && action.lastActionName.equals("menu")) {
                break;
            }
        } while (true);

        if (lastActionName.equals("again")) {
            run();
        }
    }

    private List<AbstractRecord> search(String query) {
        List<AbstractRecord> list = new LinkedList<>();
        for (AbstractRecord record : contacts) {
            for (String field : record.getFieldList()) {
                if (Pattern.compile(query, Pattern.CASE_INSENSITIVE).matcher(record.getFieldValue(field)).matches()) {
                    list.add(record);
                    break;
                }
            }
        }
        return list;
    }
}
