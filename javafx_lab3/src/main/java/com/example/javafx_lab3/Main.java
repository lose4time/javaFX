package com.example.javafx_lab3;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class Main extends Application {

    private BarChart<String, Number> barChart;
    private TextField numSetsField;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        barChart = createChart();
        root.setCenter(barChart);

        numSetsField = new TextField();
        Button addDataButton = new Button("Добавить данные");
        addDataButton.setOnAction(e -> showInputDialog());


        Button clearChartButton = new Button("Очистить диаграмму");
        clearChartButton.setOnAction(e -> clearChart());

       // root.setBottom(numSetsField);
        root.setLeft(addDataButton);
        root.setBottom(clearChartButton);

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setTitle("Диаграмма");
        primaryStage.show();
    }

    private BarChart<String, Number> createChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        barChart = new BarChart<>(xAxis, yAxis);

        return barChart;
    }

    private void showInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Введите данные");
        dialog.setHeaderText(null);
        dialog.setContentText("Введите значение:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(data -> addData(Integer.parseInt(data)));
    }

    private void addData(int newData) {
        if (barChart.getData().isEmpty()) {
            // Если нет набора данных, создайте новый
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            barChart.getData().add(series);
        }



        XYChart.Data<String, Number> newDataPoint = new XYChart.Data<>(Integer.toString(barChart.getData().get(0).getData().size() + 1), newData);

        barChart.getData().get(0).getData().add(newDataPoint);
        for(Node N:barChart.lookupAll(".default-color0.chart-bar")){
            N.setStyle("-fx-bar-fill:green;");
        }

        animateDataPoint(newDataPoint);
    }



    private void clearChart() {
        for (XYChart.Series<String, Number> set : barChart.getData()) {
            for (XYChart.Data<String, Number> dataPoint : set.getData()) {
                animateDataPointRemoval(dataPoint);
            }
        }
        barChart.getData().clear();
    }

    private void animateDataPointRemoval(XYChart.Data<String, Number> dataPoint) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(dataPoint.YValueProperty(), dataPoint.getYValue())),
                new KeyFrame(new Duration(500), new KeyValue(dataPoint.YValueProperty(), 0))
        );
        timeline.play();
    }

    private void animateDataPoint(XYChart.Data<String, Number> dataPoint) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(dataPoint.YValueProperty(), 0)),
                new KeyFrame(new Duration(500), new KeyValue(dataPoint.YValueProperty(), dataPoint.getYValue()))
        );
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}