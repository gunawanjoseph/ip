package HawkerUncle.storage;
import HawkerUncle.task.TaskList;
import HawkerUncle.task.Task;
import HawkerUncle.task.ToDo;
import HawkerUncle.task.Deadline;
import HawkerUncle.task.Event;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {
    private final String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);

        f.getParentFile().mkdirs();
        if (!f.exists()) {
            f.createNewFile();
        }

        Scanner s = new Scanner(f);

        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
                case "T":
                    tasks.add(new ToDo(description, isDone));
                    break;
                case "D":
                    LocalDateTime by = parseDateTime(parts[3]);
                    tasks.add(new Deadline(description, by, isDone));
                    break;
                case "E":
                    LocalDateTime from = parseDateTime(parts[3]);
                    LocalDateTime to = parseDateTime(parts[4]);
                    tasks.add(new Event(description, from, to, isDone));
                    break;
                default:
                    System.out.println("Unknown task type in file: " + type);
            }
        }

        s.close();
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); ++i){
            fw.write(tasks.get(i).toSaveFormat() + System.lineSeparator());
        }
        fw.close();
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }

}
