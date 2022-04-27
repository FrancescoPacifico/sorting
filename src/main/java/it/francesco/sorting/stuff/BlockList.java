package it.francesco.sorting.stuff;

import it.francesco.sorting.app.Main;

import java.util.Arrays;
import java.util.List;

public class BlockList {

    public static final int NUMBER_OF_BLOCKS = (int) ((Main.WIDTH * (100 - Main.HORIZONTAL_FREE_SPACE_PERCENTAGE) / 100) / Block.WIDTH);

    private Block[] blockList;

    public BlockList() {
        reset();
    }

    public void reset() {
        blockList = new Block[NUMBER_OF_BLOCKS];
        double absoluteY = Main.LINE_HEIGHT;
        double absoluteX = (Main.WIDTH * Main.HORIZONTAL_FREE_SPACE_PERCENTAGE / 100) / 2;
        for(int i = 0; i < blockList.length; i++, absoluteX += Block.WIDTH)
            blockList[i] = new Block(absoluteX, absoluteY, i);
    }

    public int length() {
        return blockList.length;
    }

    public Block get(int index) {
        return blockList[index];
    }

    public List<Block> getAllBlocks() {
        return Arrays.asList(blockList);
    }

    public void swapBlocks(int i, int j) {
        Block first = blockList[i];
        double firstX = first.getX();
        blockList[i].setX(blockList[j].getX());
        blockList[j].setX(firstX);
        blockList[i] = blockList[j];
        blockList[j] = first;
    }

    public void pause(int time) {
        try {
            Thread.sleep(time);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sort(SortingAlgorithm sortingAlgorithm) {
        sortingAlgorithm.getAlgorithm().accept(this);
    }

}
