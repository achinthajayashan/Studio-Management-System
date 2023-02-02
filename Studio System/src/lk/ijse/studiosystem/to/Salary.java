package lk.ijse.studiosystem.to;

public class Salary {
    private String salary_id;
    private String month;

    private double value;
    private String emp_id;



    public Salary() {
    }

    public Salary(String salary_id, String month,double value, String emp_id) {
        this.salary_id = salary_id;
        this.month = month;
        this.value = value;
        this.emp_id = emp_id;
    }

    public String getSalary_id() {
        return salary_id;
    }

    public void setSalary_id(String salary_id) {
        this.salary_id = salary_id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }


    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
