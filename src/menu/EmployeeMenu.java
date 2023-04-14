package menu;

import service.EmployeeService;

import java.util.Locale;
import java.util.Scanner;

import static service.EmployeeService.getEmployeeServiceInstance;

public class EmployeeMenu {

    String menu = """
                    Welcome to Employee Management System
                    -------------------------------------
                    1. Create an Employee
                    2. See all Employees
                    3. Update details of existing employee
                    4. Delete an employee details
                    5. Exit
                    ---------------------------------------
                    Please select the desired number from the above menu\040
                    """;
    Scanner scan = new Scanner(System.in);

    public void showMenu(){
        int choice;
        System.out.println(menu);
        while(scan.hasNext()){
            choice = scan.nextInt();
                switch (choice){
                    case 1 -> createEmployees(scan);
                    case 2 -> seeAllEmployees();
                    case 3 -> updateDetailsOfCurrentEmployee(scan);
                    case 4 -> deleteEmployee(scan);
                    case 5 -> leaveApplication();
                    default -> System.out.println("Number chosen out of range. Please select a number between 1 and 5.");
                }
            }
            showMenu();
    }

    public void createEmployees(Scanner scan){
        try {
            System.out.println("Please enter your first name: ");
            String fname = scan.nextLine().strip();//the first name is not being printed on the screen. Why?
            scan.nextLine();
            System.out.println("Please enter your last name: ");
            String lname = scan.nextLine().strip();
            System.out.println("Please enter your age: ");
            int age = scan.nextInt();
            System.out.println("Please enter your 10-digit phone number: ");
            String phoneNumber = scan.next();
            phoneNumber = checkPhoneNumberLength(phoneNumber);
            scan.nextLine();
            System.out.println("Please enter your personal email address: ");
            String personalEmail = scan.nextLine();
            System.out.println("Please confirm the fields entered: \n" +
                    "Name: " + fname+" "+lname +
                    "\nAge: " + age +
                    "\nPhone Number: " + phoneNumber +
                    "\n Alternate Email: " + personalEmail);
            System.out.println("All the details are confirmed?(y/n): ");
            char userChoice = scan.nextLine().charAt(0);
            EmployeeService employeeService = getEmployeeServiceInstance();
            if (userChoice == 'y') {
                employeeService.createAnEmployee(fname, lname, age, phoneNumber, personalEmail);
            } else if (userChoice == 'n') {
                System.out.println("Which field would you like to change?");
                String userAnswer = scan.nextLine();
                switch (userAnswer.toLowerCase()) {
                    case "first name" -> {
                        System.out.println("Please enter the confirm first name: ");
                        fname = scan.nextLine();
                    }
                    case "last name" -> {
                        System.out.println("Please enter the confirm last name: ");
                        lname = scan.nextLine();
                    }
                    case "age" -> {
                        System.out.println("Please enter the confirm age: ");
                        age = scan.nextInt();
                    }
                    case "phone number" -> {
                        System.out.println("Please enter the confirm phone number: ");
                        phoneNumber = scan.nextLine();
                        phoneNumber = checkPhoneNumberLength(phoneNumber);
                    }
                    case "personal email" -> {
                        System.out.println("Please enter the confirm alternative email address: ");
                        personalEmail = scan.nextLine();
                    }
                    default -> System.out.println("Changes completed.");
                }
                employeeService.createAnEmployee(fname, lname, age, phoneNumber, personalEmail);
            }
        }catch (NullPointerException e){
            e.getLocalizedMessage();
        }
        System.out.println();
        showMenu();
    }

    private String checkPhoneNumberLength(String phNumber){
        if(!phNumber.matches("[0-9]+")){
            System.out.println("Invalid number");
        }
        if(phNumber.length() == 10){
            System.out.println("Phone number accepted!");
        }else{
            System.out.println("Please enter the correct phone number: ");
            phNumber = scan.nextLine();
        }
        return phNumber;
    }

    public void seeAllEmployees(){
        EmployeeService employeeService = getEmployeeServiceInstance();
        System.out.println(employeeService.getAllEmployees());
        System.out.println();
        showMenu();
    }

    public void updateDetailsOfCurrentEmployee(Scanner scan){
        System.out.println("Please enter the employee id: ");
        String employee_id = scan.nextLine(); //already takes a null value. Why?
        EmployeeService employeeService = getEmployeeServiceInstance();
        System.out.println(employeeService.getEmployeeDetails(employee_id));
        System.out.println("Which field would you like to update?");
        String userAnswer = scan.nextLine();
        String fname = "",lname = "",personalEmail = "",wEmail ="", phoneNumber = "";
        int age = 0;
        switch (userAnswer.toLowerCase(Locale.ROOT)) {
            case "first name" -> {
                System.out.println("Please enter the confirm first name: ");
                fname = scan.nextLine();
            }
            case "last name" -> {
                System.out.println("Please enter the confirm last name: ");
                lname = scan.nextLine();
            }
            case "age" -> {
                System.out.println("Please enter the confirm age: ");
                age = scan.nextInt();
            }
            case "phone number" -> {
                System.out.println("Please enter the confirm phone number: ");
                phoneNumber = scan.nextLine();
                String pNumber = phoneNumber;
                phoneNumber = checkPhoneNumberLength(pNumber);
            }
            case "personal email" -> {
                System.out.println("Please enter the confirm alternative email address: ");
                personalEmail = scan.nextLine();
            }
            case "work email" -> {
                System.out.println("Please enter the correct work email address: ");
                wEmail = scan.nextLine();
            }
            default -> System.out.println("Changes completed.");
        }
        employeeService.updateDetails(fname,lname,age,phoneNumber,personalEmail,employee_id,wEmail);
        System.out.println("PFB the updated details: \n" +
                "Name: "+fname.concat(lname)+
                "\nAge: "+age+
                "\nPhone Number: "+phoneNumber+
                "\n Alternate Email: "+personalEmail);
        System.out.println();
        showMenu();
    }

    public void deleteEmployee(Scanner scan){
        EmployeeService employeeService = getEmployeeServiceInstance();
        System.out.println("Please enter the employee id of the employee to be deleted: ");
        String emp_id = scan.nextLine();
        System.out.println(employeeService.getEmployeeDetails(emp_id));
        System.out.println("Please confirm if you want to delete the details(y/n): ");
        char userChoice = scan.nextLine().charAt(0);
        if(userChoice == 'y'){
            employeeService.deleteAnEmployee(emp_id);
        }else if(userChoice == 'n'){
            System.out.println("No data deleted.");
        }
        System.out.println();
        showMenu();
    }

    public void leaveApplication(){
        System.exit(0);
    }

}
