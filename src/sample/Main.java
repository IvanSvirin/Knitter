package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Knitter");
//        primaryStage.setScene(new Scene(root, 600, 400));
//        primaryStage.show();


        Group root = new Group();
        Scene target = new Scene(root, 400, 200);
        target.setFill(Color.LIGHTGREEN);

        final Text source = new Text(50, 100, "DRAG ME");
        source.setScaleX(2.0);
        source.setScaleY(2.0);

        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Dragboard db = source.startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();
                content.putString(source.getText());
                db.setContent(content);
                event.consume();
            }
        });

        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });

        target.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                    target.setFill(Color.GREEN);
                }
                event.consume();
            }
        });

//        target.setOnDragExited(new EventHandler<DragEvent>() {
//            public void handle(DragEvent event) {
//                target.setFill(Color.BLACK);
//                event.consume();
//            }
//        });

        target.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    source.setX(event.getX());
                    source.setY(event.getY());
//                    source.setX(150);
//                    source.setY(100);
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        source.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.COPY) {
//                    source.setX(150);
//                    source.setY(100);
                }
                event.consume();
            }
        });

        root.getChildren().add(source);
        primaryStage.setScene(target);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
