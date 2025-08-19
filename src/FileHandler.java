import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public void saveExpenses(List<Expense> expenses , String filePath){
        try(BufferedWriter writer  = new BufferedWriter(new FileWriter(filePath))){
            for(Expense e : expenses){
                writer.write(e.getDate() + "," + e.getCategory() + "," + e.getAmount());
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
            }
    public List<Expense> loadExpenses(String filePath){
        List<Expense> expenses = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                    String [] parts = line.split(",");
                LocalDate date = LocalDate.parse(parts[0]);
                String category = parts[1];
                double amount = Double.parseDouble(parts[2]);
                Expense e = new Expense(category , amount , date);
                expenses.add(e);

            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return expenses;
    }

    public void saveSummary(String summary , String filePath){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(filePath,true))){
            bf.write(summary);
            bf.newLine();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
