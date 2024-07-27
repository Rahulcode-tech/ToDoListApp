import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoList {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static ArrayList<Boolean> completedTasks = new ArrayList<>();
    private static DefaultListModel<String> listModel = new DefaultListModel<>();
    private static JList<String> taskList;

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("To-do List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Create the task list
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Create the input field for new tasks
        JTextField taskInput = new JTextField(20);

        // Create the add button
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskInput.getText();
                if (!task.isEmpty()) {
                    tasks.add(task);
                    completedTasks.add(false); // New tasks are not completed
                    listModel.addElement(task);
                    taskInput.setText("");
                }
            }
        });

        // Create the delete button
        JButton deleteButton = new JButton("Delete Selected Task");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    tasks.remove(selectedIndex);
                    completedTasks.remove(selectedIndex);
                    listModel.remove(selectedIndex);
                }
            }
        });

        // Create the delete completed button
        JButton deleteCompletedButton = new JButton("Delete Completed Tasks");
        deleteCompletedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = completedTasks.size() - 1; i >= 0; i--) {
                    if (completedTasks.get(i)) {
                        tasks.remove(i);
                        completedTasks.remove(i);
                        listModel.remove(i);
                    }
                }
            }
        });

        // Create a panel for input and buttons
        JPanel panel = new JPanel();
        panel.add(taskInput);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(deleteCompletedButton);

        // Add components to the frame
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
