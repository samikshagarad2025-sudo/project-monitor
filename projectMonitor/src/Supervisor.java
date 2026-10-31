
public class Supervisor extends User {

    private String designation;

    public Supervisor(int userId, String name, String email,
                      String designation) {

        super(userId, name, email, "Supervisor");

        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void displaySupervisor() {
        displayUser();
        System.out.println("Designation: " + designation);
    }
}