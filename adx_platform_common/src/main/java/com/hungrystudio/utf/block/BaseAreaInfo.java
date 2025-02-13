package com.hungrystudio.utf.block;

public // 盘面信息类
class BaseAreaInfo extends BaseSignInfo {
    int[][] areaArray;
    int areaLengthSize;
    int areaWidthSize;
    private Integer complexAttribute = null; // 复杂属性，初始为 null

    public BaseAreaInfo(int[][] originArray) {
        if (originArray == null || originArray.length == 0) {
            areaArray = new int[0][0];
        } else {
            areaLengthSize = originArray.length;
            areaWidthSize = originArray[0].length;
            areaArray = new int[areaLengthSize][areaWidthSize];
            for (int i = 0; i < areaLengthSize; i++) {
                System.arraycopy(originArray[i], 0, areaArray[i], 0, areaWidthSize);
            }
        }
        convertArray();
    }

    public void convertArray() {
        for (int i = 0; i < areaLengthSize; i++) {
            for (int j = 0; j < areaWidthSize; j++) {
                if (areaArray[i][j] > -1) {
                    areaArray[i][j] = squareBlockSign;
                } else {
                    areaArray[i][j] = squareEmptySign;
                }
            }
        }
    }

    public int getComplexAttribute() {
        if (complexAttribute == null) {
            // 进行复杂计算
            complexAttribute = calculateComplexAttribute();
        }
        return complexAttribute;
    }

    private int calculateComplexAttribute() {
        // 这里可根据实际情况添加复杂计算逻辑
        return 0;
    }
}
