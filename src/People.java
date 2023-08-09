public class People {
    private String surname;
    private String name;
    private String patronymic;
    private String dateOfBirth;
    private long phoneNumber;
    private Gender gender;
    public People(String surname, String name, String patronymic, String dateOfBirth, long phoneNumber, Gender gender){
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
    public People() {
    }
    public String getName() {
        return name;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public Gender getGender() {
        return gender;
    }
    public String getSurname() {
        return surname;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setSex(Gender gender) {
        this.gender = gender;
    }
    @Override
    public String toString() {
        return surname + " " + name + " " + patronymic + " " + dateOfBirth + " " + phoneNumber + " " + gender;
    }
}
