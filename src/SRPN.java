/**
 * Program class for an SRPN calculator.
 */
public class SRPN {
  public void processCommand(String s, Queue queue) {
    Input input = new Input(queue);
    input.handleInput(s);
  }
}