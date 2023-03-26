package contacts;
public class CountAction extends AbstractAction {
    protected CountAction() {
        super("count");
    }

    @Override
    protected void run() {
        System.out.printf("The Phone Book has %d records.\n\n", contacts.size());
    }
}
