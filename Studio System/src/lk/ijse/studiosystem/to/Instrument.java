package lk.ijse.studiosystem.to;

public class Instrument {
    private String instrument_id;
    private String instrument_name;
    private String team_id;

    public Instrument(String instrument_id, String instrument_name, String team_id) {
        this.instrument_id = instrument_id;
        this.instrument_name = instrument_name;
        this.team_id = team_id;
    }

    public Instrument() {
    }

    public String getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(String instrument_id) {
        this.instrument_id = instrument_id;
    }

    public String getInstrument_name() {
        return instrument_name;
    }

    public void setInstrument_name(String instrument_name) {
        this.instrument_name = instrument_name;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }
}
