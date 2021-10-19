package eg.com.uofcanada.medical.common;

public class Appointment implements ComboElement{
    int year;
    int month;
    int day;
    String patientComment;
    String nurseComment;
    int status;
    int id;

    public Appointment()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getPatientComment() {
        return patientComment;
    }

    public void setPatientComment(String patientComment) {
        this.patientComment = patientComment;
    }

    public String getNurseComment() {
        return nurseComment;
    }

    public void setNurseComment(String nurseComment) {
        this.nurseComment = nurseComment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr()
    {
        switch(this.status)
        {
            case 1: return "submitted";
            case 2: return "IT approved";
            case 3: return "nurse approved";
            case 5: return "rejected";
            default: return "No Status";
        }
    }


    @Override
    public String getShortString() {
        return day+"\\"+month+"\\"+year;
    }

    @Override
    public String toString() {
        return getShortString();
    }
}
