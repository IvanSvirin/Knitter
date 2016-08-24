package sample;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;

import static sample.Main.*;


public class Controller implements EventHandler {
    @FXML
    private void openLoopsPane() {
    }

    @FXML
    @Override
    public void handle(Event event) {
        MouseEvent mouseEvent = (MouseEvent) event;
        if (mouseEvent.getX() > 100) {
            source = secondLoop;
            setDD();
        }
    }
}
