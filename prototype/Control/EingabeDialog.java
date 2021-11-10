package org.hbrs.se1.ws21.uebung4.prototype.Control;

import org.hbrs.se1.ws21.uebung4.prototype.Model.Container;
import org.hbrs.se1.ws21.uebung4.prototype.Model.Employee;
import org.hbrs.se1.ws21.uebung4.prototype.Model.Exceptions.ContainerException;
import org.hbrs.se1.ws21.uebung4.prototype.Model.Exceptions.PersistenceException;
import org.hbrs.se1.ws21.uebung4.prototype.View.EmployeeView;

import java.util.Scanner;

public class EingabeDialog {


    private static Container c = Container.getInstance();


    public EingabeDialog() {
    }


    public void eingabe() throws ContainerException, Exception, PersistenceException {

        String input = null;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Willkommen in Employee-Tool!");

        while (true) {

            System.out.print("Bitte einen Befehl eingeben" +
                    "\n>");

            input = scanner.nextLine();

            String[] strings = input.split(" ");

            if (strings[0].equals("help")) {
                System.out.println("Folgende Befehle stehen zur Verfuegung:" +
                        "\nhelp: alle moeglische Befehle anzeigen" +
                        "\nenter: neuer Employee einfuegen" +
                        "\nstore: Employee abspeichern" +
                        "\nload: die gespeicherte Employees laden" +
                        "\ndump: die nach ID sortierte Employees ausgeben" +
                        "\nsearch: suche nach Expertise" +
                        "\nexit: Program enden");
            }
            if (strings[0].equals("dump")) {
                try {
                    EmployeeView view = new EmployeeView();
                    //System.out.println("Vorname\t\t\t\tName\t\t\t\tID\t\t\t\tJob Title\t\t\t\tAbteilung");
                    view.ausgabe();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (strings[0].equals("enter")) {
                Employee emp = new Employee();
                try {
                    System.out.println("Vorname:");
                    String vorname = scanner.next();
                    emp.setVorname(vorname);
                    System.out.println("name:");
                    String name = scanner.next();
                    emp.setName(name);
                    System.out.println("Employee ID (1-100):");
                    int id = scanner.nextInt();
                    if (id < 1) {
                        System.out.println("ID ist ungueltig. Bitte eine gueltige ID eingeben: ");
                        id = scanner.nextInt();
                    }
                    emp.setPid(id);
                    System.out.println("Job Title:");
                    String jobTitle = scanner.next();
                    emp.setJobTitle(jobTitle);
                    System.out.println("Abteilung:");
                    String abteilung = scanner.next();
                    emp.setAbteilung(abteilung);
                    System.out.println("Exp 1:");
                    String expertise1 = scanner.next();
                    System.out.println("Level(1-3): ");
                    int level1 = scanner.nextInt();
                    emp.addExpertise(expertise1, level1);
                    System.out.println("Exp 2:");
                    String expertise2 = scanner.next();
                    System.out.println("Level(1-3): ");
                    int level2 = scanner.nextInt();
                    emp.addExpertise(expertise2, level2);
                    System.out.println("Exp 3:");
                    String expertise3 = scanner.next();
                    System.out.println("Level(1-3): ");
                    int level3 = scanner.nextInt();
                    emp.addExpertise(expertise3, level3);
                    c.addEmployee(emp);
                } catch (Exception e) {
                    System.out.println("der Wert ist ungueltig");

                }
            }

            if (strings[0].equals("store")) {
                c.store();
                System.out.println("abgespeichert!");

            }

            if (strings[0].equals("search")) {
                System.out.println("Bitte einen filter auswaelen" +
                        "\nexp: nach Expertise" +
                        "\nabt: nach Abteilung");
                String fil = scanner.nextLine();
                if (fil.equals("exp")) {
                    System.out.println("Bitte eine Expertise Eingeben");
                    String exp = scanner.nextLine();
                    EmployeeView.filter(exp);
                }


                if (fil.equals("abt")) {
                    System.out.println("Bitte eine Abteilung Eingeben");
                    String abt = scanner.nextLine();
                    EmployeeView.filterAbteilung(abt);
                }

            }

            if (strings[0].equals("load")) {
                System.out.println("Merge oder Force?");
                String nxt = scanner.next();
                if (nxt.equals("merge")) {
                    c.mergeLoad();
                }
                if (nxt.equals("force")) {
                    c.forceLoad();
                }
                System.out.println("Laden abgeschlossen");
            }
            if (strings[0].equals("exit")) {
                System.out.println("Aufwiedersehen :)");
                System.exit(1);
            }
        }
    }
}