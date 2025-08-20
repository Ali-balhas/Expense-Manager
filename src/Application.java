import java.time.LocalDate;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Application {
        static ExpenseManager manager = new ExpenseManager();
    public static void main(String[] args) {
//        Expense expense = new Expense("Daily", 150.3, LocalDate.now());
//        Expense expense2 = new Expense("Bills", 200, LocalDate.of(2025, 8, 1));


//        manager.addExpense(expense);
//        manager.addExpense(expense2);

//        fh.loadExpenses("Expenses.csv");
//        fh.saveCSV(manager.getExpenses(), "Expenses.csv");
//        manager.exportToTxt(fh);

        FileHandler fh = new FileHandler();
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Personal Expense Tracker!");

        while (true) {
            manager.printMenu();
            UserAction action = manager.readChoice(scan);
            if (action == null) continue;


            switch (action) {
                case ADD_EXPENSE:
                    manager.newExpense(scan, fh);

                    break;
                case VIEW_EXPENSES:
                    manager.viewExpenses(fh);
                    break;
                case VIEW_MONTHLY_SUMMARY:
                    System.out.println(manager.getMonthlySummary(fh));
                    break;
                case EXPORT_TO_TXT:
                    manager.exportToTxt(fh);
                    break;
                case EXIT:
                    ExpenseManager.exit(scan);
                    return; // exit program
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }


        }
    }


}