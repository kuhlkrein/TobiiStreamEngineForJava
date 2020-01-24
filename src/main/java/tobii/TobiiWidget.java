package tobii;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;

public class TobiiWidget extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.initStyle(StageStyle.TRANSPARENT);

        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = defaultToolkit.getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        Pane root = new Pane();
        root.setMouseTransparent(true);
        primaryStage.setAlwaysOnTop(true);
        Scene scene = new Scene(root,screenWidth,screenHeight, Color.TRANSPARENT);
        scene.getRoot().setStyle("-fx-background-color: transparent");
        primaryStage.setScene(scene);
        primaryStage.show();


        launchScreenDisplayer(root.getChildren(),screenWidth,screenHeight);

    }

    public static void launchScreenDisplayer(ObservableList<Node> root,double screenWidth, double screenHeight){

        Circle target = new Circle(50);

        target.setFill(Color.TRANSPARENT);
        target.setStroke(Color.INDIANRED);
        target.setOpacity(1);
        root.add(target);

        Timeline t = new Timeline(new KeyFrame(Duration.ONE, e -> {
            float[] position = Tobii.gazePosition();

            float xRatio = position[0];
            float yRatio = position[1];

            int xPosition = (int) (xRatio * screenWidth);
            int yPosition = (int) (yRatio * screenHeight);

           // System.out.println(xPosition + " ; " + yPosition);

            target.setCenterX(xPosition);
            target.setCenterY(yPosition);
        }));
        t.setCycleCount(Animation.INDEFINITE);

        t.play();

    }
}
