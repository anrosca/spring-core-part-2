package report;

import org.springframework.stereotype.Component;

@Component
public class A {
    private B b;

    public void setB(B b) {
        this.b = b;
    }
}

@Component
class B {
    private A a;

    public void setA(A a) {
        this.a = a;
    }
}