# Growler Project
**A Java-based CLI task management assistant**

---

## **Introduction**
**Growler** is a simple and interactive **command-line chatbot** that helps you manage your daily tasks efficiently. You can add different types of tasks, mark them as done, and list them in an organized manner. Growler is built using Java and adopts a step by step development process.

---

### **Features**
- **Task Management**
    - **ToDo**: Simple tasks without a date (e.g., `todo read book`)
    - **Deadline**: Tasks with a due date (e.g., `deadline submit assignment /by Sunday`)
    - **Event**: Tasks with a start and end time (e.g., `event group meeting /from 9pm /to 11pm`)
- **Task Completion**
    - Mark tasks as done (`mark 2`) or not done (`unmark 2`), according to the index of the task 
- **Task Search** 
    - Finds and displays all tasks which contains a keyword 
- **Task Deletion**
    - Deletes task according to its index (e.g., `delete 2`)
- **Task Listing**
    - View all added tasks (`list`)
- **User-friendly CLI**
    - Growler interacts with users through an easy-to-use command-line interface.

---

## **Setting Up in IntelliJ IDEA**
### **Prerequisites**
- **JDK 17** (Ensure Java 17 is installed)
- **Latest version of IntelliJ IDEA**

### **Installation Steps**
1. **Open IntelliJ IDEA**
    - If a project is already open, go to `File` > `Close Project` to return to the welcome screen.

2. **Import the Project**
    - Click **Open**
    - Select the project directory
    - Click **OK** and accept all default settings.

3. **Set up the correct JDK**
    - Go to **`File` > `Project Structure` > `Project`**
    - Set **Project SDK** to `JDK 17`
    - Set **Project language level** to `SDK default`
    - Click `Apply` and `OK`

4. **Run Growler**
    - Navigate to `src/main/java/Istella.java`
    - Right-click on `Growler.java`, select **Run 'Growler'**
    - If everything is set up correctly, you should see the following output:

Hello! I'm Growler

What can I do for you?


---

## **Usage Guide**
Here are some example commands to interact with Growler:

| **Command**                            | **Description**                    | **Example**                              |
|----------------------------------------|------------------------------------|------------------------------------------|
| `todo <task>`                          | Adds a **ToDo** task               | `todo buy milk`                          |
| `deadline <task> /by <date>`           | Adds a **Deadline** task           | `deadline project submission /by Sunday` |
| `event <task> /from <start> /to <end>` | Adds an **Event** task             | `event concert /from 6pm /to 10pm`       |
| `list`                                 | Displays all added tasks           | `list`                                   |
| `mark <task number>`                   | Marks a task as done               | `mark 2`                                 |
| `unmark <task number>`                 | Marks a task as not done           | `unmark 2`                               |
| `delete <task number>`                 | Deletes task specified index       | `delete 2`                               |
| `find <keyword>`                       | Finds all tasks containing keyword | `find book`                              |
| `bye`                                  | Exits Growler                      | `bye`                                    |


## **Important Notes**
- **Keep all Java files inside `src/main/java/`**
    - Do not rename or move this directory, as tools like Gradle rely on it.
- **Use Java 17**
    - Running the project with a different Java version may cause compatibility issues.

---

## **Further Enhancements**
- Implement **reminders** (reminds user of task deadlines/upcoming events)
- Implement **Categorization of tasks** (Categorizes based on work/personal/family/etc) 
- Implement **graphical user interface (GUI)** using JavaFX

---

## **Contributing**
Feel free to contribute by:
- Forking the repository
- Adding feature branches
- Submitting pull requests