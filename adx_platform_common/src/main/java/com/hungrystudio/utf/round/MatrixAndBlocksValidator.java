package com.hungrystudio.utf.round;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class MatrixAndBlocksValidator {

    // 处理矩阵和方块的有效性
    public static int processValid(String matrixStr, String blocks) {
        int matrixResult = isValidMatrix(matrixStr);
        if (matrixResult < 0) {
            return matrixResult;
        }
        int blocksResult = isValidBlocks(blocks);
        if (blocksResult < 0) {
            return blocksResult;
        }
        return 1;
    }

    // 验证方块的有效性
    public static int isValidBlocks(String blocks) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (blocks == null || blocks.isEmpty()) {
                return -200;
            }
            List<Integer> blockList = objectMapper.readValue(blocks, List.class);
            for (Object block : blockList) {
                if (!(block instanceof Integer)) {
                    return -202;
                }
                int blockInt = (int) block;
                if (blockInt < 0 || blockInt > 42) {
                    return -201;
                }
            }
            return 1;
        } catch (JsonProcessingException | ClassCastException e) {
            return -203;
        }
    }

    // 验证矩阵的有效性
    public static int isValidMatrix(String matrixStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            int[][] matrix = objectMapper.readValue(matrixStr, int[][].class);
            if (matrix.length == 8) {
                for (int[] row : matrix) {
                    if (row.length != 8) {
                        return -101;
                    }
                }
                return 1;
            } else {
                return -101;
            }
        } catch (JsonProcessingException e) {
            return -103;
        }
    }

    public static void main(String[] args) {
        String matrixStr = "[[1,-1,4,-1,1,-1,1,-1],[1,1,4,-1,1,-1,-1,-1],[-1,-1,4,6,6,6,2,2],[-1,-1,7,6,6,6,2,2],[-1,-1,7,6,6,6,2,2],[-1,-1,7,-1,-1,-1,2,2],[-1,-1,-1,-1,-1,-1,1,1],[-1,-1,1,-1,-1,-1,1,1]]";
        String matrixStr1 = "[[2,-1,-1,-1,-1,-1,2,3],[-1,3,3,-1,-1,3,2,3],[-1,-1,-1,3,3,3,3,-1],[-1,-1,-1,3,3,2,3,-1],[-1,-1,-1,-1,2,2,2,-1],[2,3,3,-1,-1,-1,-1,2],[3,3,3,-1,-1,-1,-1,2],[3,3,3,-1,2,2,2,2]]";
        String blocks = "[22,8,14]";
        int result = processValid(matrixStr, blocks);
        System.out.println(result);
    }
}
