package utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class CsvReader {

    private CsvReader(){

    }

    public static List<Map<String,String>> read(String file){
        Path path = Path.of(file);
        if (!Files.exists(path)){
            throw new IllegalArgumentException("Csv not found: "+ path.toAbsolutePath());
        }
        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).get();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
             CSVParser parser = format.parse(skipBom(reader))){

            List<Map<String,String>> rows = new ArrayList<>();
            for (CSVRecord record : parser){
                Map<String,String> values = new LinkedHashMap<>();
                for (String colunm : parser.getHeaderNames()){
                    values.put(colunm, record.isSet(colunm) ? record.get(colunm) : "");
                }
                rows.add(values);
            }
            return rows;
        } catch(IOException e){
            throw new IllegalStateException("Could not read "+ file, e);
        }
    }

    private static BufferedReader skipBom(BufferedReader reader) throws IOException {
        reader.mark(1);
        if (reader.read() != 0xFEFF) {
            reader.reset();
        }
        return reader;
    }
}