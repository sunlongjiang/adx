package com.hungrystudio.utf.block;

public // 方块类型 22 信息类，使用单例模式
class SquareType22Info extends BaseSquareInfo {
    private static final SquareType22Info instance = new SquareType22Info();

    private SquareType22Info() {
        super(new int[][]{{-1}, {-1}, {-1}, {-1}, {-1}});
        blockNum = "22";
        blockAmount = 5;
        blockShift = 0;
        blockLength = 5;
        blockWidth = 1;
    }

    public static SquareType22Info getInstance() {
        return instance;
    }
}

