import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Program class for an SRPN calculator.
 */
public class SRPN {
    Deque<Long> queue = new ArrayDeque<>();
    boolean resetRequired;

    public void processCommand(String s) {
      System.out.println("queue: " + queue);
                           
      if (NumHelper.isNumeric(s)) {
        System.out.println("isOnlyNumber?");
        queue.add(Long.parseLong(s));
      } else {
        String withoutSpaceS = s.replaceAll(" ", "");
        int inputCharLength = withoutSpaceS.length();

        if(inputCharLength == 1){
          handleOneChar(s.charAt(0));
        } else {
          handleMultipleInput(s, inputCharLength);
          System.out.println("isMultipleChar?");
        }
      }
    }

    private void handleMultipleInput (String s, int inputCharLength) {
      // String num;
      for (int i = 0; i < inputCharLength; i ++) {
        processCommand(s.substring(i, i+1));
      }
    }

    private void handleOneChar(char inputChar) {
      switch(inputChar) {
        case '=':
          resetRequired = true;
          System.out.println("= " + queue.getLast());
        break;
        case 'd':
          getAllQueue();
          System.out.println("d: " + queue.getLast());
        break;
        default:
          beforeCalculation(inputChar);
      }
    }

    private void beforeCalculation(char inputChar) {
      if (resetRequired && queue.size() > 2) {
        System.out.println("reset done!");
        queue.removeFirst();
      } else if (queue.size() == 1) {
        System.out.println("Stack underflow.");
      } else if (queue.getLast() == 0) {
        System.out.println("Divide by 0.");
      } else {
        handleCalculation(inputChar);
      }
    }

    private void getAllQueue() {
      for (int i=0; i < queue.size(); i ++) {
        long num = queue.removeFirst();
        System.out.println(num);
        queue.add(num);
      }
    }

    private void handleCalculation(char inputChar){
      System.out.println("queue in inputChar: " + queue);
      
      long stackA = queue.removeLast();
      long stackB = queue.removeLast();
      long result = 0;
      
      switch(inputChar) {
        case '+':
          result = stackB + stackA;
          break;
        case '-':
          result = stackB - stackA;
          break;
        case '/':
          result = stackB / stackA;
          break;
        case '*':
          result = stackB * stackA;
          break;
        case '^':
          result = (long) Math.pow(stackB, stackA);
          break;
        default:
      }

      long overflowCheckedResult = NumHelper.checkOverflow(result);
      queue.add(overflowCheckedResult);
    }
}