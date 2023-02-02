package lk.ijse.studiosystem.to;

public class Employee {
    private String empID;
    private String empName;
    private String empNic;
    private String empDob;
    private int empContact;
    private String empEmail;

    public Employee() {
    }

    public Employee(String empID, String empName, String empNic, String empDob, int empContact, String empEmail) {
        this.empID = empID;
        this.empName = empName;
        this.empNic = empNic;
        this.empDob = empDob;
        this.empContact = empContact;
        this.empEmail = empEmail;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNic() {
        return empNic;
    }

    public void setEmpNic(String empNic) {
        this.empNic = empNic;
    }

    public String getEmpDob() {
        return empDob;
    }

    public void setEmpDob(String empDob) {
        this.empDob = empDob;
    }

    public int getEmpContact() {
        return empContact;
    }

    public void setEmpContact(int empContact) {
        this.empContact = empContact;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }
}
