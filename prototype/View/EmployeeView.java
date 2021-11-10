package org.hbrs.se1.ws21.uebung4.prototype.View;

import org.hbrs.se1.ws21.uebung4.prototype.Model.Container;
import org.hbrs.se1.ws21.uebung4.prototype.Model.Employee;
import org.hbrs.se1.ws21.uebung4.prototype.Model.Expertise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmployeeView {


    private static Container c = Container.getInstance();
    private static List<Employee> liste = c.getCurrentList();


    public static void ausgabe() {
        liste.stream().sorted((employee1,employee2)-> Integer.compare(employee1.getPid(),employee2.getPid()));
        for (Employee employee : liste) {
            System.out.println(employee.toString());
        }

    }

    public static void filter(String exp) {
        List<Employee> filtteredEmp = new ArrayList<Employee>();
        for (Employee emp : liste) {
            List<Expertise> expList = emp.getExpertiseListe();
            if (expList.contains(exp)) {
                filtteredEmp.add(emp);
            }

        }
        for (Employee emp : filtteredEmp) {
            System.out.println(emp.toString());
        }
    }
    public static void filterAbteilung(String abt) {
        List<Employee> filtteredEmp = new ArrayList<Employee>();
        for (Employee emp : liste) {
            if(emp.getAbteilung().equals(abt)){
                filtteredEmp.add(emp);
            }

        }
        for (Employee emp : filtteredEmp) {
            System.out.println(emp.toString());
        }
    }
}
