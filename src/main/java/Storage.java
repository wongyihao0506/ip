import java.io.*;

/**
 * The Storage class handles reading and writing tasks to a file on disk.
 * It ensures that tasks are saved persistently and loaded back when the program restarts.
 */
public class Storage {
    private static final String FILE_PATH = "./data/Growler.txt";
    private static final int MAX_TASKS = 100;

    /**
     * Constructs a Storage object and ensures that the necessary file and folder structure exists.
     */
    public Storage() {
        ensureFileExists();
    }

    /**
     * Ensures that the save file and its parent folder exist.
     * If the folder or file does not exist, they are created.
     */
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

    /**
     * Saves the current list of tasks to the file.
     * Each task is written in a specific format defined by its `toFileFormat` method.
     *
     * @param taskList  The array of tasks to save.
     * @param taskCount The number of tasks in the list.
     */
    public void saveTasks(Task[] taskList, int taskCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < taskCount; i++) { // Only save valid tasks
                writer.write(taskList[i].toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the save file into the provided task array.
     * Reads each line from the file and parses it into a Task object.
     *
     * @param taskList The array to store the loaded tasks.
     * @return The number of tasks successfully loaded from the file.
     */
    public int loadTasks(Task[] taskList) {
        int taskCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
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

    /**
     * Parses a single line from the save file into a Task object.
     * Supports Todo, Deadline, and Event task types.
     *
     * @param line The line to parse.
     * @return A Task object if the line is valid, or null if the format is invalid.
     */
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
