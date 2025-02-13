package com.hungrystudio.utf.block;

public // 方块信息基类
class BaseSquareInfo extends BaseSignInfo {
    String blockNum;
    int blockAmount;
    int[][] blockArray;
    int blockShift;
    int blockLength;
    int blockWidth;

    public BaseSquareInfo(int[][] blockArray) {
        if (blockArray == null || blockArray.length == 0) {
            this.blockArray = new int[0][0];
        } else {
            this.blockLength = blockArray.length;
            this.blockWidth = blockArray[0].length;
            this.blockArray = new int[blockLength][blockWidth];
            for (int i = 0; i < blockLength; i++) {
                System.arraycopy(blockArray[i], 0, this.blockArray[i], 0, blockWidth);
            }
        }
        blockNum = "-1";
        blockAmount = -1;
        blockShift = -1;
        blockLength = -1;
        blockWidth = -1;
    }
}
