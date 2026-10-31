
public class Main {

    public static void main(String[] args) {

        // Create a student object
        Student student = new Student(
            1,
            "Rahul Patil",
            "rahul@gmail.com",
            "AIDS",
            "SY B.Tech"
        );

        // Create a supervisor object
        Supervisor supervisor = new Supervisor(
            2,
            "Prof. Sharma",
            "sharma@college.edu",
            "Project Guide"
        );

      Project project = new Project(
    101,
    "Project Monitor",
    "Academic Project Management System",
    "In Progress",
    60,
    "2026-11-15"
);
      
      
        // Create a task object
        Task task = new Task(
            201,
            "Design Dashboard",
            "08-11-2026",
            "In Progress"
        );

        // Create a feedback object
        Feedback feedback = new Feedback(
            301,
            "Project Monitor",
            "Prof. Sharma",
            "Improve the dashboard design.",
            "30-10-2026"
        );

        // Display student information
        System.out.println("===== STUDENT DETAILS =====");
        student.displayStudent();

        // Display supervisor information
        System.out.println("\n===== SUPERVISOR DETAILS =====");
        supervisor.displaySupervisor();

        // Display project information
        System.out.println("\n===== PROJECT DETAILS =====");
        project.displayProject();

        // Display task information
        System.out.println("\n===== TASK DETAILS =====");
        task.displayTask();

        // Display feedback information
        System.out.println("\n===== SUPERVISOR FEEDBACK =====");
        feedback.displayFeedback();

        // Manage projects
        ProjectManager projectManager = new ProjectManager();
        projectManager.addProject(project);

        // Manage tasks
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(task);

        System.out.println("\n===== ALL PROJECTS =====");
        projectManager.displayProjects();

        System.out.println("\n===== ALL TASKS =====");
        taskManager.displayTasks();
    }
}
