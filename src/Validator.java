import java.time.LocalDate;

public class Validator {

    public static Expense validateExpense(String input){

        String [] parts = input.split(",");
        if(parts.length != 3 ){
            System.out.println("invalid input");
            return null;
        }
        String category = parts[0].trim();
        double amount = 0;
        try{
            amount = Double.parseDouble(parts[1].trim());
            if(amount<0){
                System.out.println("amount must be positive");
                return null;
            }

        }catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
        }

        LocalDate date;
        try{
            date =LocalDate.parse(parts[2].trim());

        } catch (Exception e) {
            throw new RuntimeException(e);
//            return null;
        }
        return new Expense(category , amount , date);
    }
}
