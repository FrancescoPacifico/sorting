package it.francesco.sorting.stuff;

import it.francesco.sorting.app.Main;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Block extends Rectangle {

    public static final double WIDTH = 20;
    public static final int COLOR_LOWER_BOUND = 0;
    public static final int COLOR_UPPER_BOUND = 255;
    public static final double HEIGHT_UPPER_BOUND = Main.HEIGHT * (100 - Main.VERTICAL_FREE_SPACE_PERCENTAGE) / 100;
    public static final Color STROKE_COLOR = Color.BLACK;
    public static final double STROKE_WIDTH = 0.1;

    private int index;

    public Block(double absoluteX, double absoluteY, int index) {
        setX(absoluteX);
        setY(absoluteY);
        setWidth(WIDTH);
        setHeight(randomHeightPicker());
        setFill(randomColorPicker());
        setStroke(STROKE_COLOR);
        setStrokeWidth(STROKE_WIDTH);
        this.index = index;
    }

    private static double randomHeightPicker() {
        return new Random().nextDouble(HEIGHT_UPPER_BOUND) + 1;
    }

    private static Color randomColorPicker() {
        Random random = new Random();
        int r = random.nextInt(COLOR_UPPER_BOUND + 1);
        int g = random.nextInt(COLOR_UPPER_BOUND + 1);
        int b = random.nextInt(COLOR_UPPER_BOUND + 1);
        return Color.rgb(r, g, b);
    }

}
