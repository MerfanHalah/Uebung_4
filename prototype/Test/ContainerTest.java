package org.hbrs.se1.ws21.uebung4.prototype.Test;

import org.hbrs.se1.ws21.uebung4.prototype.Control.EingabeDialog;
import org.hbrs.se1.ws21.uebung4.prototype.Model.Container;
import org.hbrs.se1.ws21.uebung4.prototype.Model.Employee;
import org.hbrs.se1.ws21.uebung4.prototype.Strategy.PersistenceStrategyStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    Container con = Container.getInstance();
    PersistenceStrategyStream pr = new PersistenceStrategyStream();
    Employee a = new Employee(1);
    Employee b = new Employee(2);
    Employee c = new Employee(3);
    Employee d = new Employee(4);
    Employee f = new Employee(5);
    private Scanner sc = new Scanner(System.in);
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;



    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }



    @org.junit.jupiter.api.Test
    void store() {
        try {
            con.addEmployee(a);
            con.addEmployee(b);
            con.store();

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(2, con.size());
    }

    @org.junit.jupiter.api.Test
    void forceLoad() {
        assertEquals(0, con.size());
        try {
            con.forceLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(2, con.size());
    }

    @org.junit.jupiter.api.Test
    void mergeLoad() {
        try {
            con.forceLoad();
            con.addEmployee(c);
            con.store();
            con.mergeLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(3, con.size());
    }


    @org.junit.jupiter.api.Test
    void deleteEmployee() {
        try {
            con.mergeLoad();
            con.deleteEmployee(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(2, con.size());
    }


    @org.junit.jupiter.api.Test
    void main() {
        EingabeDialog ein = new EingabeDialog();
        final String sim = "help";

        try {
            ein.eingabe();
            provideInput(sim);

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}