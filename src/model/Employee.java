package model;

import java.util.Objects;
import java.util.regex.Pattern;

//Creating the base model class defining the fields and methods for the employee object.
//since i have set the fields final we cannot use the setter methods but i am running short of ideas since there are certain fields that need to be updated.
//Any other option other than setters?
public class Employee {
    private final String fName;
    private final String lName;
    private final int age;
    private final String phoneNumber;
    private String workEmail;
    private String personalEmail;
    private String employee_ID;

    private static final String emailRegex = "^(.+)@(.+).[a-z]$";
    private static final Pattern pattern = Pattern.compile(emailRegex);

    public Employee(String fName, String lName, int age, String phoneNumber, String personalEmail, String emp_id, String wEmail){
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        if(personalEmail != null){
            if(!pattern.matcher(personalEmail).matches()){
                throw new IllegalArgumentException("Incorrect email address");
            }else{
                this.personalEmail = personalEmail;
            }
            this.employee_ID = emp_id;
            this.workEmail = wEmail;
        }
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public String getEmployee_ID() {
        return employee_ID;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && phoneNumber.equals(employee.phoneNumber) && employee_ID.equals(employee.employee_ID) &&
                Objects.equals(fName, employee.fName) && Objects.equals(lName, employee.lName) &&
                Objects.equals(workEmail, employee.workEmail) && Objects.equals(personalEmail, employee.personalEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName, lName, age, phoneNumber, workEmail, personalEmail, employee_ID);
    }

    @Override
    public String toString() {
        return "Employee Details: " +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", age=" + age +
                ", phoneNumber=" + phoneNumber +
                ", workEmail='" + workEmail + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", employee_ID=" + employee_ID ;
    }
}
