
public class Student extends User {

    private String department;
    private String year;

    public Student(int userId, String name, String email,
                   String department, String year) {

        super(userId, name, email, "Student");

        this.department = department;
        this.year = year;
    }

    public String getDepartment() {
        return department;
    }

    public String getYear() {
        return year;
    }

    public void displayStudent() {
        displayUser();
        System.out.println("Department: " + department);
        System.out.println("Year: " + year);
    }
}
