package ExpenseTracker.app;
import ExpenseTracker.model.Entry;
import ExpenseTracker.model.LogManager;

import java.time.*;
import java.util.List;
import java.util.Scanner;

public class ExpensesApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean running = true;
        LocalDate date = LocalDate.now();
        LogManager logManager = new LogManager();
        logManager.loadFromFile();
        List<Entry> entries = logManager.getEntries();
        while (running) {
            System.out.println("Welcome to the Expense Logger app made by Brayan Castellanos");
            System.out.println("Please select an option");
            System.out.println("1. Add: name,amount");
            System.out.println("2. View all");
            System.out.println("3. View totals");
            System.out.println("4. Exit");
            String command = in.nextLine();
            if (command.startsWith("Add:")) {
                String[] line = command.split(":", 2);
                if (line.length != 2 || line[1].trim().isEmpty()) {
                    System.out.println("Error: Name and amount were not entered correctly.");
                    continue;
                }

                String[] entryArr = line[1].split(",");
                if (entryArr.length != 2) {
                    System.out.println("Error: Only 2 fields can be used for a new expense (name,amount).");
                    continue;
                }

                try {
                    String name = entryArr[0].trim();
                    double amount = Double.parseDouble(entryArr[1].trim());

                    Entry entry = new Entry(name, amount, date);
                    logManager.addEntry(entry);
                    System.out.println("Successfully added entry!");

                } catch (NumberFormatException e) {
                    System.out.println("Amount must be a valid number: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Validation Error: " + e.getMessage());
                }
                continue;
            }
            if (command.startsWith("View all")) {
                if (entries.isEmpty()) {
                    System.out.println("No entries recorded yet.");
                } else {
                    for (int i = 0; i < entries.size(); i++) {
                        System.out.println((i + 1) + ". " + entries.get(i));

                    }
                }
                continue;
            }
            if (command.startsWith("View totals")) {
                double total = 0;
                double dailyTotal = 0;
                for (int i = 0; i < entries.size(); i++) {
                    total += entries.get(i).getPrice();
                    if(entries.get(i).getDate().equals(LocalDate.now())){
                        dailyTotal += entries.get(i).getPrice();
                    }
                }
                System.out.println("The overall total is: $" + total );
                System.out.println("The daily total is: $" + dailyTotal);
                continue;
            }
            if (command.startsWith("Exit")){
                running = false;
                logManager.saveToFile();
                System.out.println("Date saved. Exiting...");
            }
            else {
                System.out.println("Incorrectly formatted command. Please try again");
            }
        }
        in.close();

    }
}


