package hu.florentory.main.fx;

import java.io.IOException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import hu.florentory.main.FlorentoryApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FlorentoryFxMainApp extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = new SpringApplicationBuilder(FlorentoryApplication.class)
                .web(org.springframework.boot.WebApplicationType.NONE)
                .run();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/hu/florentory/main/fx/view/MainViewFxml.fxml"));
            Scene scene = new Scene(loader.load());

            primaryStage.setTitle("Florentory - Inventory Manager");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Nem sikerült betölteni az FXML fájlt!");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}


