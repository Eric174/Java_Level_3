package Test;

public class ForTest {

    @BeforeSuite
    public void load() {
        System.out.println("Something load");
    }

    /* For test RuntimeException
    @BeforeSuite
    public void beforeLoad() {
        System.out.println("Something do before load");
    }
    */

    @Test
    public void testOne() {
        System.out.println("Test One default priority");
    }

    @Test
    public void testTwo() {
        System.out.println("Test Two default priority");
    }

    @Test
    public void testThree() {
        System.out.println("Test Three default priority");
    }

    @Test (priority = 3)
    public void testFour() {
        System.out.println("Test Four priority = 3");
    }

    @Test (priority = 9)
    private void testFive() {
        System.out.println("Test Five (private) priority = 9");
    }

    @Test (priority = 5)
    public void testSix() {
        System.out.println("Test Six priority = 5");
    }
    @Test
    public void testSeven() {
        System.out.println("Test Seven default priority");
    }

    @AfterSuite
    public void unload() {
        System.out.println("Something unload");
    }

    /* For test RuntimeException
    @AfterSuite
    public void afterUnload() {
        System.out.println("Something do after unload");
    }
    */
}
