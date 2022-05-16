package operations;

public class DivideByZeroException extends Exception
{
  /**
   * Outputs message if the user tries to divide a complex number by Zero.
   * 
   * @param message
   *          A warning telling the user that they need the denominator to be zero.
   */
  public DivideByZeroException(String message)
  {
    super(message);
  }
}
