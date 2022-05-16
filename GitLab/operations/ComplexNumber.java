package operations;

import java.text.DecimalFormat;

public class ComplexNumber implements Operation
{
  private double realNum;
  private double imagNum;
  private DecimalFormat df = new DecimalFormat("#.###");

  /**
   * Default Constructor for ComplexNumber. Makes default values zero (i.e. realNum +
   * ImagNum*i)[standard complex number format = (a + b*i)]
   */
  public ComplexNumber()
  {
    this.realNum = 0.0;
    this.imagNum = 0.0;
  }

  /**
   * Two param Constructor for ComplexNumber. (realNum + imagNum*i)[Standard compleparse qa string to double javax number format]
   * 
   * @param realNum
   *          The real number segment of a complex number
   * @param imagNum
   *          The imaginary number segment of a complex number.
   */
  public ComplexNumber(double realNum, double imagNum)
  {
    this.realNum = realNum;
    this.imagNum = imagNum;
  }

  /**
   * gets the real number segment of a complex number.
   * 
   * @return A Real Number
   */
  public double getRealNum()
  {
    return this.realNum;
  }

  /**
   * Gets the imaginary segment of a complex number.
   * 
   * @return An Imaginary Number
   */
  public double getImagNum()
  {
    return this.imagNum;
  }

  /**
   * changes the real number segment of a complex number.
   * 
   * @param realNum
   *          New value for the real number segment.
   */
  public void setRealNum(double realNum)
  {
    this.realNum = realNum;
  }

  /**
   * Changes the imaginary segment of a complex number.
   * 
   * @param imagNum
   *          New value for the imaginary number segment.
   */
  public void setImagNum(double imagNum)
  {
    this.imagNum = imagNum;
  }

  /**
   * Outputs a complex number in the form of a string.
   * 
   * @return String representation of complex number.
   */
  public String toString()
  {
    if (imagNum < 0)
    {
      return "" + df.format(realNum) + " - " + df.format(Math.abs(imagNum)) + "i";
    }
    return "" + df.format(realNum) + " + " + df.format(imagNum) + "i";
  }
}
