
import java.util.ArrayList;

public class ProjectManager {

    private ArrayList<Project> projects = new ArrayList<>();

    public void addProject(Project project) {
        projects.add(project);
        System.out.println("Project added successfully!");
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void displayProjects() {
        if (projects.isEmpty()) {
            System.out.println("No projects available.");
            return;
        }

        for (Project project : projects) {
            project.displayProject();
            System.out.println("--------------------");
        }
    }
}
