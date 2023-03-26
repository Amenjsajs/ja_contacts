package contacts;
public class DeleteAction extends AbstractAction {
    protected DeleteAction() {
        super("delete");
    }

    @Override
    protected void run() {
        if (recordSelected == null) {
            System.out.println("Please select a record to delete");
        } else {
            contacts.remove(recordSelected);

            if(save() == -1){
                contacts.add(recordSelected);
            } else {
                System.out.println("Deleted\n");
                recordSelected = null;
            }
        }
    }
}
