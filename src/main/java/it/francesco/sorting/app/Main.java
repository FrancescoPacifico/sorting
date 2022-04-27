package it.francesco.sorting.app;

import it.francesco.sorting.stuff.Block;
import it.francesco.sorting.stuff.BlockList;
import it.francesco.sorting.stuff.SortingAlgorithm;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main extends Application {

    public static final String TITLE = "SORTING";
    public static final double WIDTH = 1280;
    public static final double HEIGHT = 720;
    public static final double HORIZONTAL_FREE_SPACE_PERCENTAGE = 10;
    public static final double VERTICAL_FREE_SPACE_PERCENTAGE = 30;
    public static final double LINE_HEIGHT = (HEIGHT * VERTICAL_FREE_SPACE_PERCENTAGE / 100) / 2;
    public static final double NODES_HEIGHT = LINE_HEIGHT / 2;

    public static final Button SORT;
    public static final Button RESET;
    public static final ChoiceBox<String> ALGORITHM;

    static {
        SORT = new Button("SORT");
        SORT.setLayoutX(WIDTH / 4);
        SORT.setLayoutY(NODES_HEIGHT);
        RESET = new Button("RESET");
        RESET.setLayoutX(WIDTH / 2);
        RESET.setLayoutY(NODES_HEIGHT);
        ALGORITHM = new ChoiceBox<>();
        ALGORITHM.getItems().addAll(Arrays.stream(SortingAlgorithm.values()).map(SortingAlgorithm::getName).toList());
        ALGORITHM.getSelectionModel().selectFirst();
        ALGORITHM.setLayoutX(WIDTH / 4 * 3);
        ALGORITHM.setLayoutY(NODES_HEIGHT);
    }

    @Override
    public void start(Stage stage) {
        stage = new Stage();
        Group root = new Group();
        root.getChildren().add(new Line(0, LINE_HEIGHT, WIDTH, LINE_HEIGHT));
        BlockList blockList = new BlockList();
        root.getChildren().addAll(blockList.getAllBlocks());
        root.getChildren().addAll(SORT, RESET, ALGORITHM);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(TITLE);
        stage.show();
        SORT.setOnMouseClicked(m -> {
            new Thread(() -> {
                SORT.setDisable(true);
                RESET.setDisable(true);
                blockList.sort(SortingAlgorithm.getByName(ALGORITHM.getValue()));
                RESET.setDisable(false);
            }).start();
        });
        RESET.setOnMouseClicked(m -> {
            SORT.setDisable(false);
            blockList.reset();
            root.getChildren().removeIf(n -> n instanceof Block);
            root.getChildren().addAll(blockList.getAllBlocks());
        });
    }

    public static void main(String[] args) {
        launch();
    }

}