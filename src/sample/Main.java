package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    public static Scene target;
    public static Group root;
    public static ImageView source;
    public static ImageView firstLoop;
    public static ImageView secondLoop;
    public static TitledPane loopsPane;
    public static AnchorPane loopsAnchorPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Knitter");
        Pane pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root = new Group();
        root.getChildren().add(pane);


        target = new Scene(root, 1000, 600);
        target.setFill(Color.LIGHTGREEN);


//        loopsPane = (TitledPane) root.lookup("#loopsPane");
//        loopsAnchorPane = (AnchorPane) loopsPane.lookup("#loopsAnchorPane");
//        firstLoop = (ImageView) loopsAnchorPane.lookup("#firstLoop");
        firstLoop = (ImageView) root.lookup("#firstLoop");

        secondLoop = (ImageView) root.lookup("#secondLoop");

        source = firstLoop;
        setDD();

        primaryStage.setScene(target);
        primaryStage.show();
    }

    public static void setDD() {
        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Dragboard db = source.startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();
                content.putString(source.getId());
                content.putImage(source.getImage());
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
                    ImageView f2 = new ImageView();
                    f2.setImage(source.getImage());
                    f2.setX((((int) event.getX()) / (int) (source.getFitWidth())) * source.getFitWidth());
                    f2.setY((((int) event.getY()) / (int) (source.getFitHeight())) * source.getFitHeight());
                    root.getChildren().add(f2);
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
