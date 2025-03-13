import java.io.*;

public class Storage {
    private static final String FILE_PATH = "./data/Growler.txt";
    private static final int MAX_TASKS = 100;

    public Storage() {
        ensureFileExists();
    }

    private void ensureFileExists() {
        File file = new File(FILE_PATH);
        File folder = file.getParentFile();
        try {
            if (folder != null && !folder.exists()) {
                folder.mkdirs(); // Create folder if missing
            }
            if (!file.exists()) {
                file.createNewFile(); // Create file if missing
            }
        } catch (IOException e) {
            System.out.println("Error creating save file: " + e.getMessage());
        }
    }

    public void saveTasks(Task[] taskList, int taskCount) {
        try  (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
            for (int i = 0; i < taskCount; i++) { // Only save valid tasks
                writer.write(taskList[i].toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public int loadTasks(Task[] taskList) {
        int taskCount = 0;
        try  (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String line;
            while ((line = reader.readLine()) != null && taskCount < MAX_TASKS) {
                Task task = parseTask(line);
                if (task != null) {
                    taskList[taskCount] = task;
                    taskCount++;
                }
            }
        } catch (IOException e) {
            System.out.println("No previous task list found. Starting fresh!");
        }
        return taskCount;  // Returns taskCount, not Task[] array
    }

    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task task;
            if (type.equals("T")) {
                task = new Todo(description);
            } else if (type.equals("D")) {
                task = new Deadline(description, parts[3]);
            } else if (type.equals("E")) {
                task = new Event(description, parts[3], parts[4]);
            } else {
                return null; // Invalid format
            }
            if (isDone) {
                task.markDone();
            }
            return task;
        } catch (Exception e) {
            System.out.println("Error parsing task: " + line);
            return null;
        }
    }
}
