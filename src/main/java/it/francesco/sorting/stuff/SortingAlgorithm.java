package it.francesco.sorting.stuff;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public enum SortingAlgorithm {

    SELECTION_SORT("SELECTION SORT", blockList -> {

        for(int i = 0; i < blockList.length(); i++) {
            int minIndex = i;
            for(int j = i; j < blockList.length(); j++) {
                if(blockList.get(j).getHeight() < blockList.get(minIndex).getHeight())
                    minIndex = j;
            }
            blockList.swapBlocks(minIndex, i);
            blockList.pause(400);
        }

    }),

    BUBBLE_SORT("BUBBLE SORT", blockList -> {

        boolean swapped = true;
        while(swapped) {
            swapped = false;
            for(int i = 0; i < blockList.length() - 1; i++) {
                if(blockList.get(i).getHeight() > blockList.get(i + 1).getHeight()) {
                    blockList.swapBlocks(i, i + 1);
                    swapped = true;
                }
                blockList.pause(20);
            }
        }

    }),

    INSERTION_SORT("INSERTION SORT", blockList -> {

        for(int i = 1; i < blockList.length(); i++) {
            for(int j = i; j >= 1; j--) {
                if(blockList.get(j).getHeight() > blockList.get(j - 1).getHeight())
                    break;
                blockList.swapBlocks(j, j - 1);
                blockList.pause(20);
            }
        }

    }),

    QUICK_SORT("QUICK SORT", null) {

        @Override
        public Consumer<BlockList> getAlgorithm() {
            return blockList -> {
                quickSort(blockList, 0, blockList.length() - 1);
            };
        }

        private void quickSort(BlockList blockList, int start, int end) {
            if(end - start >= 1) {
                int pivotIndex = new Random().nextInt(start, end + 1);
                pivotIndex = partition(blockList, start, end, pivotIndex);
                quickSort(blockList, start, pivotIndex - 1);
                quickSort(blockList, pivotIndex + 1, end);
            }
        }

        private int partition(BlockList blockList, int start, int end, int pivotIndex) {
            double pivot = blockList.get(pivotIndex).getHeight();
            blockList.swapBlocks(pivotIndex, end);
            int i = start;
            int j = end - 1;
            while(i < j) {
                if(blockList.get(i).getHeight() >= pivot) {
                    blockList.swapBlocks(i, j--);
                    blockList.pause(20);
                }
                else {
                    i++;
                }
            }
            blockList.pause(20);
            if(blockList.get(i).getHeight() >= pivot) {
                blockList.swapBlocks(i, end);
                return i;
            }
            else {
                blockList.swapBlocks(i + 1, end);
                return i + 1;
            }
        }

    },

    MERGE_SORT("MERGE SORT", null) {

        @Override
        public Consumer<BlockList> getAlgorithm() {
            return blockList -> {
                mergeSort(blockList, 0, blockList.length() - 1);
            };
        }

        private void mergeSort(BlockList blockList, int start, int end) {
            if(end - start >= 1) {
                int mid = (end + start) / 2;
                mergeSort(blockList, start, mid);
                mergeSort(blockList, mid + 1, end);
                merge(blockList, start, end, mid);
            }
        }

        private void merge(BlockList blockList, int start, int end, int mid) {
            int i = start;
            int j = mid + 1;
            int k = 0;
            List<Pair<Double, Paint>> temp = new ArrayList<>(end - start + 1);
            while(i <= mid && j <= end) {
                if(blockList.get(i).getHeight() < blockList.get(j).getHeight()) {
                    temp.add(k++, new Pair<>(blockList.get(i).getHeight(), blockList.get(i++).getFill()));
                }
                else {
                    temp.add(k++, new Pair<>(blockList.get(j).getHeight(), blockList.get(j++).getFill()));
                }
            }
            while(i <= mid) {
                temp.add(k++, new Pair<>(blockList.get(i).getHeight(), blockList.get(i++).getFill()));
            }
            while(j <= end) {
                temp.add(k++, new Pair<>(blockList.get(j).getHeight(), blockList.get(j++).getFill()));
            }
            for(k = 0; k < temp.size(); k++) {
                blockList.get(start + k).setHeight(temp.get(k).getKey());
                blockList.get(start + k).setFill(temp.get(k).getValue());
                blockList.pause(20);
            }
        }

    };

    private final String name;
    private final Consumer<BlockList> algorithm;

    SortingAlgorithm(String name, Consumer<BlockList> algorithm) {
        this.name = name;
        this.algorithm = algorithm;
    }

    public String getName() {
        return name;
    }

    public Consumer<BlockList> getAlgorithm() {
        return algorithm;
    }

    public static SortingAlgorithm getByName(String name) {
        for (SortingAlgorithm a : values())
            if (a.name.equalsIgnoreCase(name))
                return a;
        return null;
    }

}
