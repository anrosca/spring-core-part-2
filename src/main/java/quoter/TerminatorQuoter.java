package quoter;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class TerminatorQuoter implements Quoter {

    @Value("${terminator.quote:Hasta la vista, baby!}")
    private String quote;

    public void sayQuote() {
        System.out.println(quote);
    }

    @Override
    public void destroy() {
        System.out.println("Destroy");
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
    }
}
