package org.hbrs.se1.ws21.uebung4.prototype.Model;

import java.util.ArrayList;
import java.util.List;

public class  Employee implements java.io.Serializable, Comparable {

    private String vorname;
    private String name;
    private Integer pID;
    private String jobTitle;
    private String abteilung;
    private List<Expertise> expertise =new ArrayList<Expertise>();

    public Employee(){}


    public Employee (int id){
        this.pID = id;
    }

    public Employee (String vorname, String name, Integer pID,String jobTitle, String abteilung){
        this.vorname = vorname;
        this.name = name;
        this.pID = pID;
        this.jobTitle = jobTitle;
        this.abteilung = abteilung;
    }


    public String getAbteilung() {
        return abteilung;
    }

    public void setAbteilung(String abteilung) {
        this.abteilung = abteilung;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pID;
    }

    public void setPid(Integer pid) {
        this.pID = pid;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void addExpertise(String name, int level){
        expertise.add(new Expertise(name,level));
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<Expertise> getExpertiseListe(){
        return expertise;
    }

    public String toString(){
        return this.vorname + "\t\t\t\t"+ this.name + "\t\t\t\t" + this.pID + "\t\t\t\t"
                + this.jobTitle +"\t\t\t\t" + this.abteilung ;

    }




    @Override
    public int compareTo(Object o) {
        return 0; // Werte: 0, 1, -1 --> Pid vergleichen!
    }
}

