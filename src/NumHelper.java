public class NumHelper {
    private static int minNum = Integer.MIN_VALUE; // -2147483648
    private static int maxNum = Integer.MAX_VALUE; // 2147483647
  
    public static boolean isNumeric(String strNum) {
        if (strNum == null) return false;
  
        try {
          Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
          return false;
        }
  
        return true;
    }
  
    public static long checkOverflow(long result) {
      if (result > maxNum) {
        return maxNum;
      } else if (result < minNum) {
        return minNum;
      }
      
      return result;
    }
  }