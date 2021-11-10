package org.hbrs.se1.ws21.uebung4.prototype.Model;
import org.hbrs.se1.ws21.uebung4.prototype.Control.EingabeDialog;
import org.hbrs.se1.ws21.uebung4.prototype.Model.Exceptions.ContainerException;
import org.hbrs.se1.ws21.uebung4.prototype.Model.Exceptions.PersistenceException;
import org.hbrs.se1.ws21.uebung4.prototype.Strategy.PersistenceStrategy;
import org.hbrs.se1.ws21.uebung4.prototype.Strategy.PersistenceStrategyStream;

import java.util.ArrayList;
import java.util.List;

public class Container {

    private PersistenceStrategy p = new PersistenceStrategyStream();
    private List<Employee> content;


    private static Container c = new Container();


    private Container() {
        content = new ArrayList<Employee>();
    }


    public static Container getInstance() {

        return c;
    }

    public void store() throws PersistenceException {

        try {

            if (p == null) {
                throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Keine Strategie von außen gesetzt");
            }

            p.save(content);
        } catch (UnsupportedOperationException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Not Implemented!");
        }
    }

    public void forceLoad() throws PersistenceException {

        content = p.load();

    }
    public void mergeLoad()throws PersistenceException {
        if(p== null)
            throw new PersistenceException( PersistenceException.ExceptionType.NoStrategyIsSet,"Strategy not initialized");
        try {

            List<Employee> tempList = p.load();
            for (Employee employee: tempList) {
                try {
                    this.addEmployee(employee);
                } catch (ContainerException e) {

                }
            }

        }
        catch (UnsupportedOperationException e){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Not implemented");
        }

    }


    public void addEmployee(Employee employee) throws ContainerException {
        Integer m = employee.getPid();

        for (int i = 0; i < content.size(); i++) {
            Integer c = content.get(i).getPid();
            if (c.equals(m)) {
                throw new ContainerException("Employee mit der ID " + c +
                        " ist bereits vorhanden!");
            }

        }
        content.add(employee);
    }

    public String deleteEmployee(int id) {
        for (int i = 0; i < content.size(); i++) {
            Integer c = content.get(i).getPid();
            if (c == id) {
                content.remove(i);
                return "Employee mit der ID " + id + " wurde gelöscht";
            }
        }
        return "Employee mit der ID " + id + " ist nicht verfuegbar";

    }


    public List<Employee> getCurrentList() {
        return content;
    }


    public int size() {
        return content.size();
    }

    public void setStrategy(PersistenceStrategy p) {

        this.p = p;
    }

    public static void main(String[] args) throws Exception {
        EingabeDialog start = new EingabeDialog();
        start.eingabe();
    }

}
