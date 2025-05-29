package main.java.com;
import main.java.com.view.AutoRun;
import main.java.com.view.Menu;


public class Application {
    public static void main(String[] args) {
        boolean useAutoTests = true;

        if (useAutoTests) { AutoRun.runTests(); }
        else { Menu.run();}
    }
}
