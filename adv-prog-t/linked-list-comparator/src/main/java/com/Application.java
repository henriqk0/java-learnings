package main.java.com;
import com.view.AutoRun;
import com.view.Menu;


public class Application {
    public static void main(String[] args) {
        boolean useAutoTests = true;

        if (useAutoTests) { AutoRun.runTests(); }
        else { Menu.run();}
    }
}
