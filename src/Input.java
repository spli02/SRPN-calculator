public class Input {
    Queue queue;
    Calculation calculation;

    boolean resetRequired = false;
    boolean isNumMinus = false;
    String num = "";

    public Input(Queue que) {
        queue = que;
        calculation = new Calculation(que);
    }

    public void handleInput(String s) {
        boolean isComment = false;
        int inputCharLength = s.length();

        for (int i = 0; i < inputCharLength; i++) {
            char currentS = s.charAt(i);
            char nextS = i != (inputCharLength - 1) ? s.charAt(i + 1) : ' ';

            // sharp should be treated as comment
            if (currentS == '#') {
                isComment = !isComment;
            }

            // space should be ignored
            if (currentS == ' ') {
                continue;
            }

            // letters which is not between # should be handled
            if (!isComment) {
                if (Character.isDigit(currentS)) {
                    handleOneNumInput(currentS, nextS);
                } else if (currentS == '-' && nextS != ' ') {
                    isNumMinus = true;
                } else {
                    handleOneCharInput(s.charAt(i));
                }
            }
        }
    }

    private void handleOneNumInput(char currentS, char nextS) {
        String currentSS = Character.toString(currentS);
        String nextSS = Character.toString(nextS);

        if (Character.isDigit(nextS)) {
            num = num == "" ? currentSS + nextSS : num + nextSS;
        } else {
            String addNum = num != "" ? num : currentSS;
            addNum = isNumMinus ? '-' + addNum : addNum;

            queue.addNew(Long.parseLong(addNum));
            isNumMinus = false;
            num = "";
        }
    }

    private void handleOneCharInput(char inputChar) {
        switch (inputChar) {
            case '=':
                resetRequired = true;
                System.out.println(queue.getLast());
                break;
            case 'd':
                queue.getAllQueue();
                break;
            case 'r':
                long randomNum = NumHelper.getRandomNum();
                if (randomNum != 0) {
                    queue.addNew(randomNum);
                } else {
                    PrintHelper.stackOverflow();
                }
                break;
            case '#':
                 break;
            case '+':
            case '-':
            case '/':
            case '*':
            case '%':
            case '^':
                resetOldValue();
                if (calculation.isCalculatable(inputChar)) {
                    calculation.handleCalculation(inputChar);
                }
                break;
            default:
                PrintHelper.noOperator(inputChar);
                break;
        }
    }

    // if the oldest value is retrieved before, delete it before calculation
    private void resetOldValue() {
        if (resetRequired && queue.getSize() > 2) {
            queue.removeFirst();
            resetRequired = false;
        }
    }
}
