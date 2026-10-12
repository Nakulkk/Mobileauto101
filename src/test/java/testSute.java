import org.testng.annotations.*;

public class testSute {

    @AfterSuite
    public void gj(){
        System.out.println("@AfterSuit");
    }
    @AfterTest
    public void gk() {
        System.out.println("@AfterTest");
    }
    @BeforeSuite
    public void j(){
        System.out.println("@BeforeSuite1");
    }
    @BeforeSuite
    public void hj(){
        System.out.println("@BeforeSuite2");
    }
    @BeforeTest
    public void hk(){
        System.out.println("@BeforeTest");
    }

    @Test
    public void test(){
        System.out.println("Test Mehod");
    }

    @AfterMethod
    public void gl(){
        System.out.println("@AfterMethod");
    }
    @AfterClass
    public void gm(){
        System.out.println("@AfterClass");
    }
    @BeforeMethod
    public void hl(){
        System.out.println("@BeforeMethod");
    }
    @BeforeClass
    public void hm(){
        System.out.println("@BeforeClass");
    }
}
