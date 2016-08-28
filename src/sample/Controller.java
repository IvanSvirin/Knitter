package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;

import static sample.Main.*;


public class Controller implements EventHandler<MouseEvent> {
    @FXML
    @Override
    public void handle(MouseEvent event) {
//        if (event.getX() > 100) {
//            source = secondLoop;
//            source.setOnDragDetected(this);
//            Dragboard db = source.startDragAndDrop(TransferMode.COPY);
//            ClipboardContent content = new ClipboardContent();
//            content.putString(source.getId());
//            content.putImage(source.getImage());
//            db.setContent(content);
//            event.consume();
//
//            setDD();
//        } else {
//            source = firstLoop;
//            source.setOnDragDetected(this);
//            Dragboard db = source.startDragAndDrop(TransferMode.COPY);
//            ClipboardContent content = new ClipboardContent();
//            content.putString(source.getId());
//            content.putImage(source.getImage());
//            db.setContent(content);
//            event.consume();
//
//            setDD();
//        }
    }
}
