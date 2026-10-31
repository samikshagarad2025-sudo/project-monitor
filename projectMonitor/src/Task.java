
public class Task {

    private int taskId;
    private String taskName;
    private String deadline;
    private String status;

    public Task(int taskId, String taskName,
                String deadline, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.deadline = deadline;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayTask() {
        System.out.println("Task ID: " + taskId);
        System.out.println("Task Name: " + taskName);
        System.out.println("Deadline: " + deadline);
        System.out.println("Status: " + status);
    }
}
