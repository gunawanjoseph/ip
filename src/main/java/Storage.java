import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
                    tasks.add(new Deadline(description, parts[3], isDone));
                    break;
                case "E":
                    tasks.add(new Event(description, parts[3], parts[4], isDone));
                    break;
                default:
                    System.out.println("Unknown task type in file: " + type);
            }
        }

        s.close();
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t: tasks){
            fw.write(t.toSaveFormat() + System.lineSeparator());
        }
        fw.close();
    }
}
