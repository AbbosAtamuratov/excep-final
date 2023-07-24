import model.Contact;

public class ContactParser {
    public static void main(String[] args) {
        Contact dude = new Contact();
        dude.parseContact("M 11.5.1997 Lewis St. John 855423");
        System.out.println(dude);
    }
}
