import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseManager {
    private List<Expense> expenses = new ArrayList<>();
    public void addExpense(Expense e){
        expenses.add(e);
    }
    public List<Expense> getExpenses(){
//        expenses.forEach(System.out::println);
        return expenses;
    }

    public String getMonthlySummary(){
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
}
