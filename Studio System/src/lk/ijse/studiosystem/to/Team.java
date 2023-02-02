package lk.ijse.studiosystem.to;

public class Team {
    private String team_id;
    private String team_name;
    private String employee_id;

    public Team() {
    }

    public Team(String team_id, String team_name, String employee_id) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.employee_id = employee_id;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }
}
