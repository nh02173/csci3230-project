package base;

import base.modules.*;
import base.modules.Runnable;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ComboBox<String> algoComboBox;
    @FXML
    public Slider sampleSizeSlider;
    @FXML
    public Slider sampleBoundSlider;
    @FXML
    public Button generateButton;
    @FXML
    public Button resetButton;
    @FXML
    public BarChart<Number, Number> simulationChart;
    @FXML
    public ProgressBar frameProgressBar;
    @FXML
    public Button previousButton;
    @FXML
    public Button nextButton;
    @FXML
    public Label statusLabel;
    @FXML
    public Label messageLabel;

    private Integer[] frameBuffer;
    private Algorithm simulation;
    private int position = 1;
    private int total;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
    }

    @FXML
    public void generateButtonClicked(Event event) {
        if (StringUtils.isEmpty(algoComboBox.getSelectionModel().getSelectedItem())) {
            // Bad input
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Select an algorithm");
        } else {
            // Setup Simulation
            this.simulation = identifySelection(algoComboBox.getSelectionModel().getSelectedItem());
            ((Runnable) this.simulation).runSimulation();
            this.total = this.simulation.FrameRecord.size();
            System.out.println("Total for the FrameRecord: " + this.total);
            statusLabel.setTextFill(Color.BLACK);
            statusLabel.setText("Generated sample set");
            algoComboBox.setDisable(true);
            sampleSizeSlider.setDisable(true);
            sampleBoundSlider.setDisable(true);
            generateButton.setDisable(true);

            // Setup Chart
            this.simulationChart.setTitle(this.simulation.Name);
            this.frameBuffer = this.simulation.FrameRecord.get(position - 1);
            paintChart(this.frameBuffer);
            paintProgress(this.total, true);
            messageLabel.setText("Frame position " + position + " of " + total);
        }
    }

    @FXML
    public void resetButtonClicked(Event event) {
        resetSimulation();
    }

    @FXML
    public void previousButtonClicked(Event event) {
        if ((position - 1) > 0) {
            position--;
            this.frameBuffer = this.simulation.FrameRecord.get(position - 1);
            paintChart(this.frameBuffer);
            paintProgress(this.total, false);
            messageLabel.setText("Frame position " + position + " of " + total);
        }
    }

    @FXML
    public void nextButtonClicked(Event event) {
        if ((position + 1) <= this.total) {
            position++;
            this.frameBuffer = this.simulation.FrameRecord.get(position - 1);
            paintChart(this.frameBuffer);
            paintProgress(this.total, true);
            messageLabel.setText("Frame position " + position + " of " + total);
        }
    }

    private void paintProgress(double input, boolean direction) {
        double increment = (100 / input) / 100;
        System.out.println("Progressing at: " + increment);
        if (direction) {
            this.frameProgressBar.setProgress(this.frameProgressBar.getProgress() + increment);
        } else {
            this.frameProgressBar.setProgress(this.frameProgressBar.getProgress() - increment);
        }
        System.out.println("Amount after progress: " + this.frameProgressBar.getProgress());
    }

    private void paintChart(Integer[] input) {
        this.simulationChart.getData().clear();
        int counter = 0;

        for (int value : input) {
            XYChart.Series currentSeries = new XYChart.Series();
            currentSeries.getData().add(new XYChart.Data(Integer.toString(counter), value));
            this.simulationChart.getData().addAll(currentSeries);
            counter++;
        }
    }

    private Algorithm identifySelection(String input) {
        System.out.println("Got input: " + input);
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
        this.position = 1;
        this.total = 0;
        algoComboBox.getSelectionModel().clearSelection();
        sampleSizeSlider.adjustValue(0);
        sampleBoundSlider.adjustValue(0);
        algoComboBox.setDisable(false);
        sampleSizeSlider.setDisable(false);
        sampleBoundSlider.setDisable(false);
        generateButton.setDisable(false);
        frameProgressBar.setProgress(0);
        this.frameBuffer = null;
        this.simulation = null;
        this.simulationChart.setTitle("");
        this.simulationChart.getData().clear();
        this.statusLabel.setTextFill(Color.BLACK);
        statusLabel.setText("Simulation Reset");
        messageLabel.setTextFill(Color.BLACK);
        messageLabel.setText("Ready For Data");
    }
}
