package service;

import model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EmployeeService {

    //first creating a singleton instance so that when the application is run multiple times, there aren't several instances created but instead only one entry
    // point for the program in the system
    private static final EmployeeService employeeServiceInstance = new EmployeeService();

    private EmployeeService(){}

    public static EmployeeService getEmployeeServiceInstance() {
        return employeeServiceInstance;
    }

    Map<String, Employee> employees = new HashMap<>();

 //Since we are creating a newly creating a new employee he cannot have an employee_id or a work email address. We created the below method to assign to the
    // newly created employee a unique id and work-email address
    private String[] createEmployeeIDAndEmail(String fname, String lname){
        Random random = new Random();
        int  count = random.nextInt(111,99999);
        String[]  empData = new String[2];
        if(fname != null && lname != null){
            empData[0] = Integer.toString(random.nextInt(0,999999999));
            empData[1] = fname+"."+lname+count+"@learnermedia.com";
        }
        return empData;
    }

    public void createAnEmployee(String fname, String lname, int age, String phoneNumber, String personalEmail){
        String[] localEmpInstance = createEmployeeIDAndEmail(fname,lname);
        employees.put(localEmpInstance[0], new Employee(fname, lname, age,phoneNumber,personalEmail,localEmpInstance[0],localEmpInstance[1]));
    }

    public Employee getEmployeeDetails(String employee_ID){
        return employees.get(employee_ID);
    }

    //facing issues in this method as I cannot work out the logic. What I want the method to do is that when update details method from the Employee Menu class is
    //called, the new details for an existing employee are updated. One way i thought of is the setter methods but for that i will have to make the fields of the
    //employee class non-final. Is that a good practice?
    //What I was trying to do int the belo method is, I was matching the present name with the updated name and if they were different,
    //then for the same employee id, I would have passed the new value for the particular field while keeping the rest of the fields unchanged.
    //I thought of a way I can work this out, but it will be a long way
    public void updateDetails(String newFirstName, String newLastName, int newAge, String newPhoneNumber, String newPersonalEmail, String emp_id, String newWorkEmail){
        Employee updatedEmployee = employees.get(emp_id);
        if(!updatedEmployee.getfName().equalsIgnoreCase(newFirstName) && updatedEmployee.getlName().equalsIgnoreCase(newLastName) &&
        updatedEmployee.getAge()== newAge && updatedEmployee.getPhoneNumber().equals(newPhoneNumber) && updatedEmployee.getWorkEmail().equalsIgnoreCase(newWorkEmail)
        && updatedEmployee.getPersonalEmail().equalsIgnoreCase(newPersonalEmail) && updatedEmployee.getEmployee_ID().equalsIgnoreCase(emp_id)) {
            //is there a way that only the new value for a particular field is updated while the rest remain the same.
            // One is the setters but then i will have to make the fields non-final. is that a good practice?
            //new First Name
            employees.put(emp_id,new Employee(newFirstName,updatedEmployee.getlName(),updatedEmployee.getAge(),updatedEmployee.getPhoneNumber(),
                    updatedEmployee.getPersonalEmail(),updatedEmployee.getWorkEmail(),updatedEmployee.getEmployee_ID()));
        //repeat checking for all the fields. :D
            //new Last Name
        }else if(updatedEmployee.getfName().equalsIgnoreCase(newFirstName) && !updatedEmployee.getlName().equalsIgnoreCase(newLastName) &&
        updatedEmployee.getAge()== newAge && updatedEmployee.getPhoneNumber().equals(newPhoneNumber) && updatedEmployee.getWorkEmail().equalsIgnoreCase(newWorkEmail)
        && updatedEmployee.getPersonalEmail().equalsIgnoreCase(newPersonalEmail) && updatedEmployee.getEmployee_ID().equalsIgnoreCase(emp_id)) {
            employees.put(emp_id, new Employee(updatedEmployee.getfName(), newLastName, updatedEmployee.getAge(), updatedEmployee.getPhoneNumber(),
                    updatedEmployee.getPersonalEmail(), updatedEmployee.getWorkEmail(), updatedEmployee.getEmployee_ID()));
        }
            //New Age. Knowing it can be derived from the DOB as well but since I do not have DOB field we will go this way.
        else if(updatedEmployee.getfName().equalsIgnoreCase(newFirstName) && updatedEmployee.getlName().equalsIgnoreCase(newLastName) &&
        updatedEmployee.getAge() != newAge && updatedEmployee.getPhoneNumber().equals(newPhoneNumber) && updatedEmployee.getWorkEmail().equalsIgnoreCase(newWorkEmail)
        && updatedEmployee.getPersonalEmail().equalsIgnoreCase(newPersonalEmail) && updatedEmployee.getEmployee_ID().equalsIgnoreCase(emp_id)){
            employees.put(emp_id,new Employee(updatedEmployee.getfName(), updatedEmployee.getlName(),newAge,updatedEmployee.getPhoneNumber(),
                    updatedEmployee.getPersonalEmail(),updatedEmployee.getWorkEmail(),updatedEmployee.getEmployee_ID()));
        }
        //New Phone Number
        else if(updatedEmployee.getfName().equalsIgnoreCase(newFirstName) && updatedEmployee.getlName().equalsIgnoreCase(newLastName) &&
        updatedEmployee.getAge()== newAge && !updatedEmployee.getPhoneNumber().equals(newPhoneNumber) && updatedEmployee.getWorkEmail().equalsIgnoreCase(newWorkEmail)
        && updatedEmployee.getPersonalEmail().equalsIgnoreCase(newPersonalEmail) && updatedEmployee.getEmployee_ID().equalsIgnoreCase(emp_id)){
            employees.put(emp_id,new Employee(updatedEmployee.getfName(), updatedEmployee.getlName(), updatedEmployee.getAge(),newPhoneNumber,
                    updatedEmployee.getPersonalEmail(),updatedEmployee.getWorkEmail(),updatedEmployee.getEmployee_ID()));
        }
        //New Personal Email address
        else if(updatedEmployee.getfName().equalsIgnoreCase(newFirstName) && !updatedEmployee.getlName().equalsIgnoreCase(newLastName) &&
        updatedEmployee.getAge() == newAge && updatedEmployee.getPhoneNumber().equals(newPhoneNumber) && !updatedEmployee.getPersonalEmail().equalsIgnoreCase(newPersonalEmail)
        && updatedEmployee.getWorkEmail().equalsIgnoreCase(newWorkEmail) && updatedEmployee.getEmployee_ID().equalsIgnoreCase(emp_id)){
            employees.put(emp_id,new Employee(updatedEmployee.getfName(), updatedEmployee.getlName(), updatedEmployee.getAge(),updatedEmployee.getPhoneNumber(),
                    newPersonalEmail,updatedEmployee.getWorkEmail(),updatedEmployee.getEmployee_ID()));
        }
        //New Work Email address
        else if(updatedEmployee.getfName().equalsIgnoreCase(newFirstName) && !updatedEmployee.getlName().equalsIgnoreCase(newLastName) &&
        updatedEmployee.getAge()== newAge && updatedEmployee.getPhoneNumber().equals(newPhoneNumber) && updatedEmployee.getPersonalEmail().equalsIgnoreCase(newPersonalEmail)
        && !updatedEmployee.getWorkEmail().equalsIgnoreCase(newWorkEmail) && updatedEmployee.getEmployee_ID().equals(emp_id)) {
            employees.put(emp_id, new Employee(updatedEmployee.getfName(), updatedEmployee.getlName(), updatedEmployee.getAge(), updatedEmployee.getPhoneNumber(),
                    newPersonalEmail, newWorkEmail, updatedEmployee.getEmployee_ID()));
        }
//Even though the above work-around works, I feel that this is not the correct way. This was possible only because I have 6 updatable fields.
// This is an unfeasible solution where there are large number of fields. Keeping that in mind, what would be the least costly approach.
    }

    public void deleteAnEmployee(String emp_id){
        //just learned that this will possibly only delete the key. What about the values? Is there a way where, by passing the key, the key
        //and its corresponding values are also deleted?
        employees.remove(emp_id);
        System.out.println("Employee details have been deleted.");
    }
    public Collection<Employee> getAllEmployees(){
        return employees.values();
    }
}
