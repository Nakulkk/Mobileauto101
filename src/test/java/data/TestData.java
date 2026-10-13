package data;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestData {

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