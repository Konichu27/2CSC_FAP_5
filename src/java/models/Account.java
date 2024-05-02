package models;

public class Account {
    String uname, pword, urole;
    Account(String uname, String pword, String urole) {
        this.uname = uname;
        this.pword = pword;
        this.urole = urole;
    }
    public String getUname() {
        return uname;
    }
    public String getPword() {
        return pword;
    }
    public String getUrole() {
        return urole;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public void setPword(String pword) {
        this.pword = pword;
    }
    public void setUrole(String urole) {
        this.urole = urole;
    }
    @Override
    public String toString() {
        return urole + " " + uname;
    }
}