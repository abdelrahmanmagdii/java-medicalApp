package eg.com.uofcanada.medical.common;

public class Patient {
    String name;
    String email;
    String password;
    int gender;
    String weight;
    int activities;
    int diet;
    String illness;
    String allergy;
    String yearOfBirth;

    public Patient()
    {

    }

    public Patient(String name, String email, String password,
                   int gender, String weight, int activities,
                   int diet, String illness, String allergy, String yearOfBirth)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.weight = weight;
        this.activities = activities;
        this.diet = diet;
        this.illness = illness;
        this.allergy = allergy;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getActivities() {
        return activities;
    }

    public void setActivities(int activities) {
        this.activities = activities;
    }

    public int getDiet() {
        return diet;
    }

    public void setDiet(int diet) {
        this.diet = diet;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", weight='" + weight + '\'' +
                ", activities=" + activities +
                ", diet=" + diet +
                ", illness='" + illness + '\'' +
                ", allergy='" + allergy + '\'' +
                '}';
    }

}
