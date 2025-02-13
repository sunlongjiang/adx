package com.hungrystudio.utf.block;

public // 方块类型 13 信息类，使用单例模式
class SquareType13Info extends BaseSquareInfo {
    private static final SquareType13Info instance = new SquareType13Info();

    private SquareType13Info() {
        super(new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}});
        blockNum = "13";
        blockAmount = 9;
        blockShift = 0;
        blockLength = 3;
        blockWidth = 3;
    }

    public static SquareType13Info getInstance() {
        return instance;
    }
}
