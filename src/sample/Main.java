package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.List;
import javax.swing.JFrame;

public class Main extends Application{

    public static void main(String[] args) {


        // TODO Auto-generated method stub

        JFrame obj = new JFrame();
        GamePlay gamePlay = new GamePlay();
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Brick Breaker_Herych");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
    }

    @Override
    public void init() throws Exception {

        System.out.println("Application inits");
        super.init();
    }
    @Override
    public void start(Stage stage) {

        System.out.println("Application starts");

        // получаем переданные параметры
        Application.Parameters params = getParameters();
        List<String> unnamedParams = getParameters().getUnnamed();
        int i =0;
        for(String param: unnamedParams){
            i++;
            System.out.printf("%d - %s \n", i, param);
        }

        stage.show();
    }
    @Override
    public void stop() throws Exception {

        System.out.println("Application stops");
        super.stop();
    }
}