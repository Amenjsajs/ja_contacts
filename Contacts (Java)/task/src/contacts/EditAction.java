package contacts;

import java.io.IOException;
import java.util.List;

public class EditAction extends AbstractAction {
    protected EditAction() {
        super("edit");
    }

    @Override
    protected void run() {
        if (recordSelected == null) {
            System.out.println("Please select a record to edit");
        } else {
            displayMenu();
            String field = input.nextLine();

            System.out.printf("Enter %s: ", field);

            String value = input.nextLine();
            String oldValue = recordSelected.getFieldValue(field);
            recordSelected.setField(field, value);

            if (save() == -1) {
                recordSelected.setField(field, oldValue);
            } else {
                System.out.println("Save");
                System.out.println(recordSelected);
            }
        }
    }

    @Override
    public void displayMenu() {
        if (menu == null) {
            final List<String> fields = recordSelected.getFieldList();
            menu = new StringBuilder("Select a field (").append(fields.get(0));
            for (int i = 1, len = fields.size(); i < len; i++) {
                menu.append(", ").append(fields.get(i));
            }
            menu.append("): ");
        }
        System.out.print(menu);
    }
}
