public class Calculation {
    Queue queue;

    public Calculation(Queue que) {
        queue = que;
    }

    // detect potential calculation error
    public boolean isCalculatable(char inputChar) {
        boolean calculatable = false;
        long queueLastLetter = queue.getLast();

        if (queue.getSize() == 1) {
            PrintHelper.stackUnderflow();
        } else if (queueLastLetter == 0 && inputChar == '/') {
            PrintHelper.divideZero();
        } else if (queueLastLetter == 0 && inputChar == '%') {
            PrintHelper.noJob();
        } else {
            calculatable = true;
        }

        return calculatable;
    }

    public void handleCalculation(char inputChar) {
        long stackA = queue.removeLast();
        long stackB = queue.removeLast();
        long result = 0;

        switch (inputChar) {
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
            case '%':
                result = stackB % stackA;
                break;
            case '^':
                result = (long) Math.pow(stackB, stackA);
                break;
            default:
        }

        long overflowCheckedResult = NumHelper.checkOverflow(result);
        queue.addNew(overflowCheckedResult);
    }
}
