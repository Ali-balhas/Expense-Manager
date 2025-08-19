import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();
        FileHandler fh = new FileHandler();
        fh.loadExpenses("Expenses.csv");
        Expense expense = new Expense("Daily", 150.3, LocalDate.now());
        Expense expense2 = new Expense("Bills", 200, LocalDate.of(2025, 8, 1));


        manager.addExpense(expense);
        manager.addExpense(expense2);
        fh.saveExpenses(manager.getExpenses(), "Expenses.csv");

        fh.saveSummary(manager.getMonthlySummary(), "Summary.txt");

        System.out.println("Welcome to Personal Expense Tracker!");

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add a new expense");
            System.out.println("2. View all expenses");
            System.out.println("3. View monthly summary");
            System.out.println("4. Export summary to a text file");
            System.out.println("5. Exit");

            System.out.print("Enter your choice (1-5): ");
            String input = scan.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Please enter you expense in this order  Category, Amount, Date (yyyy-MM-dd)");
                    String e = scan.nextLine();
                    String[] res = e.split(",");
                    String category = res[0];
                    double amount = Double.parseDouble(res[1]);
                    LocalDate date = LocalDate.parse(res[2]);
                    Expense newExpense = new Expense(category, amount, date);
                    manager.addExpense(newExpense);
                    fh.saveExpenses(manager.getExpenses(), "Expenses.csv");
                    System.out.println("Added successfully");


                    break;
                case "2":
                    manager.getExpenses().forEach(System.out::println);
                    break;
                case "3":
                    System.out.println(manager.getMonthlySummary());
                    break;
                case "4":
                    fh.saveSummary(manager.getMonthlySummary(), "Summary.txt");
                    break;
                case "5":
                    System.out.println("Goodbye!");
                    scan.close();
                    return; // exit program
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }


        }
    }
}