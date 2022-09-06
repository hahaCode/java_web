package com.fan.algo;

/**
 * https://leetcode.com/problems/reverse-integer/
 *
 */
public class ReverseInteger {
    public static void main(String[] args) {
        RT_Solution1 solution1 = new RT_Solution1();
        //System.out.println(Integer.MAX_VALUE);
        System.out.println(solution1.reverse(-120));

    }
}

class RT_Solution1 {
    public int reverse(int x) {
        int result = 0;
        boolean negative = false;
        long xx = x;
        if(x < 0) {
            negative = true;
            xx = Math.abs((long)x);
        }

        long temp = 0;
        while(true) {
            temp = temp * 10 + (xx%10);
            if(temp > Integer.MAX_VALUE) {
                return 0;
            }
            xx = xx/10;
            if(xx == 0) {
                break;
            }
        }

        if(temp > Integer.MAX_VALUE) {
            return 0;
        }
        if(negative == true) {
            temp = -temp;
        }

        result = (int) temp;
        return result;
    }
}

class RT_Solution2 {
    // 不可以用long
    public int reverse(int x) {  //-2147483648  -- 2147483647
        int result = 0;
        while (x != 0) {
            int mod = x % 10;

            // 处理越界  result > 214748364
            if(result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && mod > 7)) // 正向越界
                return 0;

            // result < -214748364
            if(result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && mod < -8)) // 反向越界
                return 0;
            result = result * 10 + mod;
            x /= 10;
        }
        return result;
    }
}
