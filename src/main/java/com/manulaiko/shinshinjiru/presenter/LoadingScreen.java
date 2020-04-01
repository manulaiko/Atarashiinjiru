package com.manulaiko.shinshinjiru.presenter;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.springframework.stereotype.Controller;

/**
 * Loading screen presenter.
 * =========================
 *
 * Handles the loading screen logic.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@Controller
public class LoadingScreen {
    @FXML
    private Label loading;

    /**
     * Initializes the rotate transition in the loading label.
     */
    @FXML
    private void initialize() {
        var transition = new RotateTransition(Duration.seconds(1), loading);
        transition.setByAngle(360);
        transition.setDuration(Duration.seconds(2));
        transition.setDelay(Duration.ZERO);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.play();
    }
}
