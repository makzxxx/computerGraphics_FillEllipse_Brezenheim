package cg.task;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EllipseDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        EllipseFiller filler = new EllipseFiller();

        filler.fillEllipseByBoundingBox(gc, 100, 100, 400, 200, Color.RED, Color.YELLOW);

        filler.fillEllipse(gc, 600, 300, 150, 100, Color.BLUE, Color.LIGHTBLUE);

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Ellipse Filler Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
