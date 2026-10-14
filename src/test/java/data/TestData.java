package data;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CsvReader;
import utils.ExcleReader;

import java.util.List;
import java.util.Map;

public class TestData {

    private static final String EXCLE_FILE = "logins.xlsx";
    private static final String EXCLE_SHEET = "logins.xls";
    private static final String CSV_FILE = "logins.csv";

    @DataProvider(name = "invalidLoginsExcel",parallel = true)
    public static Object[][] invalidLoginExcel() {
        return toTestUser(ExcleReader.read(EXCLE_FILE, EXCLE_SHEET));
    }

    @DataProvider(name = "invalidLoginsCsv",parallel = true)
    public static Object[][] invalidLoginCsv() {
        return toTestUser(CsvReader.read(CSV_FILE));
    }

    private static Object[][] toTestUser(List<Map<String, String>>rows) {
        Object[][] data = new Object[rows.size()][1];
        for (int i = 0; i < rows.size(); i++) {
            data[i][0] = rows.get(i);
        }
        return data;
    }

    @DataProvider(name = "invalidlogin")
    public Object[][] invalidlogins() {
        return new Object[][] {
                {"Locked_out_user", "secret_sauce", "Locked out"},
                {"standard_user", "wrong_password", "do not match"},
                {"", "secret_sauce", "Username is required"},
                {"standard_user", "", "Password is required"}
        };
    }

}