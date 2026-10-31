
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Task added successfully!");
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (Task task : tasks) {
            task.displayTask();
            System.out.println("--------------------");
        }
    }
}
