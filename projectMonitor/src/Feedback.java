
public class Feedback {

    private int feedbackId;
    private String projectName;
    private String supervisorName;
    private String message;
    private String date;

    public Feedback(int feedbackId, String projectName,
                    String supervisorName, String message,
                    String date) {

        this.feedbackId = feedbackId;
        this.projectName = projectName;
        this.supervisorName = supervisorName;
        this.message = message;
        this.date = date;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public void displayFeedback() {
        System.out.println("Project: " + projectName);
        System.out.println("Supervisor: " + supervisorName);
        System.out.println("Feedback: " + message);
        System.out.println("Date: " + date);
    }
}
