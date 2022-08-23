package com.fan.algo;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        LCP_Solution4 solution1 = new LCP_Solution4();
        System.out.println(solution1.longestCommonPrefix(new String[]{"","b"}));
    }
}

class LCP_Solution1 {

    /**
     * 横向扫描: 依次遍历字符串数组中的每个字符串, 对于每个遍历到的字符串, 更新最长公共前缀, 当遍历完所有字符串以后, 就可以得到字符串数组中的最长公共前缀
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";

        //将第一个字符串设置为初始前缀
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {

            String currentStr = strs[i];

            int k = 0;
            while (k < Math.min(prefix.length(), currentStr.length())) {
                if(prefix.charAt(k) != currentStr.charAt(k)) {
                    break;
                }
                k++;
            }
            prefix = prefix.substring(0, k);  // substring 的区间是左闭右开
        }

        return prefix;
    }
}

class LCP_Solution2 {
    /***
     * 纵向扫描: 纵向扫描时, 从前往后遍历所有字符串的每一列, 比较相同列上的字符是否相同, 如果相同则继续对下一列进行比较, 如果不相同则当前列不再属于公共前缀, 当前列之前的部分
     * 为最长公共前缀
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";

        StringBuilder prefix = new StringBuilder();

        int maxLength = strs[0].length(); // 最长公共前缀的最大长度 一定小于等于 字符串数组的最小长度
        for (int i = 1; i < strs.length; i++) {
            if (maxLength > strs[i].length()) {
                maxLength = strs[i].length();
            }
        }

        for (int i = 0; i < maxLength; i++) {
            char c = strs[0].charAt(i);   // 拿到第一个字符串的第i个字符
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) { //拿到其他字符串的第i个字符
                    return prefix.toString();
                }
            }
            prefix.append(c);
        }

        return prefix.toString();
    }
}

class LCP_Solution3 {
    /**
     * 分治法
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";

        return longestCommonPrefix(strs, 0, strs.length-1);
    }

    /**
     * 求从strs[start] 到 strs[end] 中的最长公共前缀
     * @param strs
     * @param start 起始位置
     * @param end 终止位置
     * @return
     */
    private String longestCommonPrefix(String[] strs, int start, int end){

        if(start == end) {
            //只传过来一个字符串
            return strs[start];
        } else {
            int mid = (start+end)/2;
            String lcpLeft = longestCommonPrefix(strs, start, mid);
            String lcpRight = longestCommonPrefix(strs, mid+1, end);
            return twoStringsLongestCommonPrefix(lcpLeft, lcpRight);
        }
    }

    /**
     * 求两个字符串的最长公共前缀
     * @param str1
     * @param str2
     * @return
     */
    private String twoStringsLongestCommonPrefix(String str1, String str2) {
        int maxLength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < maxLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }
        return str1.substring(0, maxLength);
    }
}

class LCP_Solution4 {
    /**
     * 二分查找: 用maxLength表示字符串数组的最短字符串长度, 则可以在[0, maxLength)范围内通过二分查找得到最长公共前缀的长度
     *
     * 每次取查找范围的中间值mid, 判断每个字符串的长度为mid前缀是否相同, 如果相同则最长公共前缀的长度一定大于或等于mid, 如果不相同则最长公共前缀的长度一定小于mid
     * 通过上述方式将查找范围缩小一半, 直到得到最长公共前缀的长度
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        int maxLength = strs[0].length(); //字符串数组的最短字符串长度
        for (String str : strs) {
            maxLength = maxLength - str.length() > 0 ? str.length():maxLength;
        }

        int low = 0;
        int high = maxLength;
        // 有点绕 没有理解透彻
        while (low <= high) {
            int mid = (low+high) / 2;
            if(isCommonPrefix(strs, mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return strs[0].substring(0, (low + high)/2);
    }

    /**
     * 判断是否是公共前缀
     * @return
     */
    private boolean isCommonPrefix(String[] strs, int len) {
        String curPrefix = strs[0].substring(0, len);
        for (int i = 1; i < strs.length; i++) {
            if(!strs[i].startsWith(curPrefix))
                return false;
        }
        return true;
    }
}