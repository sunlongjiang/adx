package com.hungrystudio.utf.block;

public // 方块类型 11 信息类，使用单例模式
class SquareType11Info extends BaseSquareInfo {
    private static final SquareType11Info instance = new SquareType11Info();

    private SquareType11Info() {
        super(new int[][]{{-1, -1, -1, -1, -1}});
        blockNum = "11";
        blockAmount = 5;
        blockShift = 0;
        blockLength = 1;
        blockWidth = 5;
    }

    public static SquareType11Info getInstance() {
        return instance;
    }
}

