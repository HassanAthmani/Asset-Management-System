/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asset_management_system.usedAlot;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author User
 */
public class notification {
    
    public void flash(Node node, String message) {
        Font font = Font.font("Verdana", FontWeight.NORMAL, 20);
        Color boxColor = Color.GREY;
        Color textColor = Color.WHITE;
        double duration = 8000;
        double arcH = 5;
        double arcW = 5;
 
        final Rectangle rectangle = new Rectangle();
        final Text text = new Text(message);
 
 
        double x = 0;
        double y = 0;
        text.setLayoutX(x);
        text.setLayoutY(y);
        text.setFont(font);
        text.setFill(textColor);
 
        Scene scene = node.getScene();
        final Parent p = scene.getRoot();
 
        if (p instanceof Group) 
        {
            Group group = (Group) p;
            group.getChildren().add(rectangle);
            group.getChildren().add(text);
        }
        if (p instanceof Pane) 
        {
            Pane group = (Pane) p;
            group.getChildren().add(rectangle);
            group.getChildren().add(text);
        }
 
        Bounds bounds = text.getBoundsInParent();
 
        double sWidth = scene.getWidth();
        double sHeight = scene.getHeight();
 
        x = sWidth / 2 - (bounds.getWidth() / 2);
        y = sHeight / 2 - (bounds.getHeight() / 2);
        text.setLayoutX(x);
        text.setLayoutY(y);
        bounds = text.getBoundsInParent();
        double baseLineOffset = text.getBaselineOffset();
 
 
        rectangle.setFill(boxColor);
        rectangle.setLayoutX(x - arcW);
        rectangle.setLayoutY(y - baseLineOffset - arcH);
        rectangle.setArcHeight(arcH);
        rectangle.setArcWidth(arcW);
        rectangle.setWidth(bounds.getWidth() + arcW * 2);
        rectangle.setHeight(bounds.getHeight() + arcH * 2);
 
        FadeTransition ft = new FadeTransition(
                Duration.millis(duration), rectangle);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
        ft.setOnFinished(new EventHandler<ActionEvent>() {
 
            public void handle(ActionEvent event) {
                if (p instanceof Group) {
                    Group group = (Group) p;
                    group.getChildren().remove(rectangle);
                    group.getChildren().remove(text);
                }
                 if (p instanceof Pane) {
                    Pane group = (Pane) p;
                    group.getChildren().remove(rectangle);
                    group.getChildren().remove(text);
                }
            }
        });
        FadeTransition ft2 = new FadeTransition(
                Duration.millis(duration + (duration * .1)), text);
        ft2.setFromValue(1.0);
        ft2.setToValue(0.0);
        ft2.play();
    }
    
}
