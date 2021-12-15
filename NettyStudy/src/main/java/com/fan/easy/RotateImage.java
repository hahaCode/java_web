package com.fan.easy;

public class RotateImage {
    public static void main(String[] args) {
        RI_Solution1 solution1 = new RI_Solution1();
        //solution1.rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        solution1.rotate(new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}});
    }
}

class RI_Solution1 {
    public void rotate(int[][] matrix) {

        //一层一层的旋转,定义四个边界值
        int top = 0;
        int left = 0;
        int bottom = matrix.length - 1;
        int right = matrix.length - 1;

        int n = matrix.length; // n表示当前处理的层  每层有多少个元素
        while (n > 1) {
            // 按层旋转
            for (int i = 0; i < n-1; i++) {
                int temp = matrix[top][left+i];
                matrix[top][left+i] = matrix[bottom-i][left];
                matrix[bottom-i][left] = matrix[bottom][right-i];
                matrix[bottom][right-i] = matrix[top+i][right];
                matrix[top+i][right] = temp;
            }
            //上层处理完毕之后, 向里收缩
            top++;
            left++;
            bottom--;
            right--;
            n = n-2; //里面一层比外面一层少了两个元素
        }

        // 这部分不需要提交
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
