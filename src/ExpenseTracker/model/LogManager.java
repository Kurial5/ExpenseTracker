package ExpenseTracker.model;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.*;


public class LogManager {
    private List<Entry> entries = new ArrayList<>();
    private final String filePath = "entries.csv";

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public  List <Entry> getEntries(){
        return entries;
    }

    public void loadFromFile() {
        File file = new File(filePath);
        if(file.exists()) {
            try (Scanner in = new Scanner(file, StandardCharsets.UTF_8)) {
                entries.clear();
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    addEntry(Entry.fromCsv(line));
                }

            }

        catch (IOException e) {
            System.out.println("File operation failed: " + e.getMessage());
        }
        }
    }
    public void saveToFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (Entry entry : entries){
                writer.write(entry.toCsv());
                writer.newLine();
            }

            }
             catch (IOException e){
                System.out.println("File operation failed: " + e.getMessage());
        }
    }
}
