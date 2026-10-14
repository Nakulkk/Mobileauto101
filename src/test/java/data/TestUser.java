package data;

import java.util.Map;

public class TestUser {

    public TestUser(String name, String password, String expected) {
    }

    public record Testuser(String name , String pass, String error){
        public static TestUser fromRow(Map<String,String>row){
            return new TestUser(row.get("name"),row.get("password"),row.get("Expected"));
        }
        public String toString(){
            return name;
        }
    }
}
