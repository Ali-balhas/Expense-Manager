public interface CSVFormatter<T> {
    String csvFormat();
    T readCsv(String line);
}
