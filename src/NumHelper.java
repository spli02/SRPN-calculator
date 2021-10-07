public class NumHelper {
  private static int minNum = Integer.MIN_VALUE; // -2147483648
  private static int maxNum = Integer.MAX_VALUE; // 2147483647
  private static long[] randomNums = {1804289383, 846930886, 1681692777, 1714636915, 1957747793, 424238335,
      719885386, 1649760492, 596516649, 1189641421, 1025202362, 1350490027, 783368690, 1102520059,
      2044897763, 1967513926, 1365180540, 1540383426, 304089172, 1303455736, 35005211, 521595368,1804289383};
  private static int randomCount = -1;

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

  public static long getRandomNum() {
    randomCount++;
    if(randomNums.length > randomCount) {
      return randomNums[randomCount];
    } else {
      return 0;
    }
  }
}