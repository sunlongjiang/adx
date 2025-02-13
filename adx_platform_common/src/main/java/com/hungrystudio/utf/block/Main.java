package com.hungrystudio.utf.block;

class Main {
    static int targetLineLength = 8;
    static int targetWidthLength = 8;
    static SquareType11Info squareType11Info = SquareType11Info.getInstance();
    static SquareType13Info squareType13Info = SquareType13Info.getInstance();
    static SquareType22Info squareType22Info = SquareType22Info.getInstance();

    public static boolean fillJudgeDict(int[][] originFillArray, int areaLengthSize, int areaWidthSize, BaseSquareInfo squareInfo, int originI, int originJ) {
        if (originI + squareInfo.blockLength > areaLengthSize || originJ + squareInfo.blockWidth - squareInfo.blockShift > areaWidthSize || originJ - squareInfo.blockShift < 0) {
            return false;
        }
        for (int i = 0; i < squareInfo.blockLength; i++) {
            for (int j = 0; j < squareInfo.blockWidth; j++) {
                if (squareInfo.blockArray[i][j] != squareInfo.squareBlockSign) {
                    continue;
                }
                int targetX = originI + i;
                int targetY = originJ + j - squareInfo.blockShift;
                // 检查目标位置是否在数组范围内
                if (targetX < 0 || targetX >= originFillArray.length || targetY < 0 || targetY >= originFillArray[0].length) {
                    return false;
                }
                if (originFillArray[targetX][targetY] != squareInfo.squareEmptySign) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int calculateLineWeight(int[][] areaInfoArray, int length, int width, boolean isRow, int originSquareEmptySign) {
        int weightValue = 0;
        for (int i = 0; i < length; i++) {
            int lineValue = 0;
            int fillValue = 0;
            for (int j = 0; j < width; j++) {
                int value = isRow ? areaInfoArray[i][j] : areaInfoArray[j][i];
                if (value != originSquareEmptySign) {
                    fillValue++;
                } else {
                    if (j == 0) {
                        int nextValue = isRow ? areaInfoArray[i][j + 1] : areaInfoArray[j + 1][i];
                        if (nextValue != originSquareEmptySign) {
                            lineValue++;
                        }
                    } else if (j == width - 1) {
                        int prevValue = isRow ? areaInfoArray[i][j - 1] : areaInfoArray[j - 1][i];
                        if (prevValue != originSquareEmptySign) {
                            lineValue++;
                        }
                    } else {
                        int nextValue = isRow ? areaInfoArray[i][j + 1] : areaInfoArray[j + 1][i];
                        int prevValue = isRow ? areaInfoArray[i][j - 1] : areaInfoArray[j - 1][i];
                        if (nextValue != originSquareEmptySign) {
                            lineValue++;
                        }
                        if (prevValue != originSquareEmptySign) {
                            lineValue++;
                        }
                    }
                }
            }
            weightValue += fillValue * lineValue;
        }
        return weightValue;
    }

    public static int getAreaValue(int[][] areaInfoArray) {
        int gongNum = 0;
        double rowNum = 0;
        double colNum = 0;
        int emptyNum = 0;
        int originSquareEmptySign = squareType11Info.squareEmptySign; // 提取公共表达式
        boolean[][] cache = new boolean[targetWidthLength][targetLineLength]; // 缓存判断结果

        for (int i = 0; i < targetWidthLength; i++) {
            for (int j = 0; j < targetLineLength; j++) {
                if (areaInfoArray[i][j] == originSquareEmptySign) {
                    emptyNum++;
                    if (!cache[i][j]) {
                        if (fillJudgeDict(areaInfoArray, targetWidthLength, targetLineLength, squareType11Info, i, j)) {
                            rowNum++;
                        }
                        if (fillJudgeDict(areaInfoArray, targetWidthLength, targetLineLength, squareType13Info, i, j)) {
                            gongNum++;
                        }
                        if (fillJudgeDict(areaInfoArray, targetWidthLength, targetLineLength, squareType22Info, i, j)) {
                            colNum++;
                        }
                        cache[i][j] = true;
                    }
                }
            }
        }

        int rowWeightValue = calculateLineWeight(areaInfoArray, targetWidthLength, targetLineLength, true, originSquareEmptySign);
        int colWeightValue = calculateLineWeight(areaInfoArray, targetLineLength, targetWidthLength, false, originSquareEmptySign);

        int baseWeightValue = colWeightValue + rowWeightValue;
        double clearWeightValue;
        if (gongNum >= 3) {
            clearWeightValue = gongNum + 20;
        } else {
            clearWeightValue = gongNum * 2 + rowNum / 3 + colNum / 3 + 16;
        }
        int tempEmptyValue = (emptyNum - 32) * (emptyNum - 32);
        double tempWeightValue = baseWeightValue - clearWeightValue;
        double strengthWeightValue;
        if (emptyNum >= 32) {
            strengthWeightValue = tempWeightValue - tempEmptyValue / 5.0;
        } else {
            if (gongNum > 0) {
                strengthWeightValue = tempWeightValue + tempEmptyValue / 5.0;
            } else {
                strengthWeightValue = tempWeightValue + tempEmptyValue / 2.0;
            }
        }
        return (int) Math.floor(strengthWeightValue + 246);
    }

    public static int getAreaComplexValue(String originListStr) {
        int usrComplexValue = -1;
        try {
            int[][] originList = parseStringToArray(originListStr);
            BaseAreaInfo roundAreaInfo = new BaseAreaInfo(originList);
            usrComplexValue = getAreaValue(roundAreaInfo.areaArray);
        } catch (Exception e) {
        }
        return usrComplexValue;
    }

    public static int[][] parseStringToArray(String input) {
        String[] rows = input.substring(2, input.length() - 2).split("],\\[");
        int[][] result = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] elements = rows[i].split(",");
            result[i] = new int[elements.length];
            for (int j = 0; j < elements.length; j++) {
                result[i][j] = Integer.parseInt(elements[j]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        String test = "[[10,10,10,-1,-1,-1,10,10],[10,10,10,-1,-1,-1,10,10],[10,10,10,-1,-1,-1,10,10],[10,10,10,-1,-1,-1,10,10],[10,10,10,-1,-1,-1,10,10],[10,10,10,-1,-1,-1,10,10],[10,10,10,-1,-1,-1,10,10],[10,10,10,-1,-1,-1,10,10]]";
        String test = "[[-1,-1,-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,3,3,3],[-1,-1,-1,-1,-1,3,3,3],[-1,-1,-1,-1,-1,3,3,3]]";
        System.out.println(test + " 测试成功");
        long start = System.currentTimeMillis();
        System.out.println(getAreaComplexValue(test));
        long end = System.currentTimeMillis();
        System.out.println("Execution time: " + (end - start) / 1000.0 + " seconds");

        getAreaComplexValue("\"\"");


    }
}
