package quoter;

public class TerminatorQuoter implements Quoter {

    private String message;

    @Override
    public void sayQuote() {
        System.out.println(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private void init() {
        sayQuote();
    }

    private void destroy() {
        System.out.println("Destroy");
    }
}
