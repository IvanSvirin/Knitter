package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;

import java.util.EventListener;

import static sample.Main.root;
import static sample.Main.target;


public class Controller implements EventListener{
    ImageView source;
    @FXML
    private void openLoopsPane() {
    }


    @FXML
    public void handle(Event event) {
        source = (ImageView) event.getSource();

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

}
