
public class Project {

    private int projectId;
    private String projectName;
    private String description;
    private String status;
    private int progress;
    private String deadline;

   public Project(int projectId, String projectName,
               String description, String status, int progress,
               String deadline) {
    this.projectId = projectId;
    this.projectName = projectName;
    this.description = description;
    this.status = status;
    this.progress = progress;
    this.deadline = deadline;
}

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
    public String getDeadline() {
    return deadline;
}

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        if (progress >= 0 && progress <= 100) {
            this.progress = progress;
        } else {
            this.progress = 0;
        }
    }

    public void displayProject() {
        System.out.println("Project ID: " + projectId);
        System.out.println("Project Name: " + projectName);
        System.out.println("Description: " + description);
        System.out.println("Status: " + status);
        System.out.println("Progress: " + progress + "%");
    }
}
