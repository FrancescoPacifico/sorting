package it.francesco.sorting.stuff;

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

    });

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
