import java.time.Year;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    public void addExpense(Expense e){
        expenses.add(e);
    }

    public List<Expense> getExpenses(FileHandler fh){
//        expenses.forEach(System.out::println);
//        return expenses;
        return fh.loadCSV("Expenses.csv" ,Validator::validateExpense);
    }

    public String getMonthlySummary(FileHandler fh){
        List<Expense> expenses = getExpenses(fh);
        Map<YearMonth , Double> summary = expenses.stream()
                .collect(Collectors.groupingBy(
                        expense -> YearMonth.from(expense.getDate()),
                        Collectors.summingDouble(Expense::getAmount)
                ));

        StringBuilder sb = new StringBuilder();
//        System.out.println("Monthly Summary :");
        sb.append("Monthly Summary \n");
        summary.forEach((month , total) -> sb.append(month).append(" ").append(total).append("\n"));
        return sb.toString();
    }

    static void exit(Scanner scan) {
        System.out.println("Goodbye!");
        scan.close();
        return;
    }

    void exportToTxt(FileHandler fh) {
        fh.saveSummary(getMonthlySummary(fh), "Summary.txt");
        System.out.println("Exported successfully");
    }

    void viewExpenses(FileHandler fh) {
        getExpenses(fh).forEach(System.out::println);
    }

    void newExpense(Scanner scan, FileHandler fh) {
        System.out.println("Please enter you expense in this order  Category, Amount, Date (yyyy-MM-dd)");
        String e = scan.nextLine();

        Expense newExpense = Validator.validateExpense(e);
        if (newExpense != null) {
            List<Expense> expenses = getExpenses(fh);
            expenses.add(newExpense);
            fh.saveCSV(expenses, "Expenses.csv");
            System.out.println("Added successfully");
        }
    }

    public  void printMenu() {
        System.out.println("\n=== Library Menu ===");
        UserAction[] userActions = UserAction.values();
        for (UserAction userAction : userActions) {
            Action action = userAction.getAction();
            System.out.println(action.code() +" - " + action.message());
        }

        System.out.print("Choose an option: ");
    }
    public  UserAction readChoice(Scanner sc) {
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            return null;
        }
        return Arrays.stream(UserAction.values()).filter(v -> v.getAction().code()==choice).findFirst().orElse(null);
    }
}
