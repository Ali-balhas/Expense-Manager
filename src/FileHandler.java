import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FileHandler {
    public void saveCSV(List<? extends CSVFormatter> formatters , String filePath){
        try(BufferedWriter writer  = new BufferedWriter(new FileWriter(filePath))){
            for(CSVFormatter f : formatters){
                writer.write(f.csvFormat());
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
            }
    public <T extends CSVFormatter> List<T> loadCSV(String filePath , Function<String , T> validator){
        List<T> list = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){

                T object = validator.apply(line);
                list.add(object);

            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void saveSummary(String summary , String filePath){
        try(BufferedWriter bf = new BufferedWriter(new FileWriter(filePath))){
            bf.write(summary);
            bf.newLine();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }


}
