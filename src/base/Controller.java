package base;

import base.modules.*;
import base.modules.Runnable;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Queue;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ComboBox algoComboBox;
    @FXML
    public Slider sampleSizeSlider;
    @FXML
    public Slider sampleBoundSlider;
    @FXML
    public Button generateButton;
    @FXML
    public Button resetButton;
    @FXML
    public BarChart simulationChart;
    @FXML
    public ProgressBar frameProgressBar;
    @FXML
    public Button previousButton;
    @FXML
    public Button nextButton;
    @FXML
    public Label messageLabel;

    private Integer[] frameBuffer;
    private Algorithm simulation;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private int position = 0;
    private int total;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

    }

    public void generateButtonClicked(Event event) {
        this.simulation = identifySelection(algoComboBox.getSelectionModel().getSelectedItem().toString());
        ((Runnable)this.simulation).runSimulation();
        this.total = this.simulation.FrameRecord.size();
    }

    public void resetButtonClicked(Event event) {
        resetSimulation();
    }

    public void previousButtonClicked(Event event) {

    }

    public void nextButtonClicked(Event event) {

    }

    private Algorithm identifySelection(String input) {
        switch (input) {
            case "Selection Sort":
                return new Selection(sampleSizeSlider.getValue(), sampleBoundSlider.getValue());
            case "Insertion Sort":
                return new Insertion(sampleSizeSlider.getValue(), sampleBoundSlider.getValue());
            case "Bubble Sort":
                return new Bubble(sampleSizeSlider.getValue(), sampleBoundSlider.getValue());
            case "Quick Sort":
                return new Quick(sampleSizeSlider.getValue(), sampleBoundSlider.getValue());
            case "Merge Sort":
                return new Merge(sampleSizeSlider.getValue(), sampleBoundSlider.getValue());
            default:
                throw new IllegalArgumentException("Cannot evaluate the selection provided.");
        }
    }

    private void resetSimulation() {
        algoComboBox.getSelectionModel().clearSelection();
        sampleSizeSlider.adjustValue(0);
        sampleBoundSlider.adjustValue(0);
        frameProgressBar.setProgress(0);
        this.frameBuffer = null;
        this.simulation = null;
        this.xAxis = new CategoryAxis();
        this.yAxis = new NumberAxis();
        this.simulationChart = new BarChart<String, Number>(xAxis, yAxis);
        messageLabel.setText("Simulation Reset");
    }
}
