package operations;

import java.util.ResourceBundle;

public interface Operation {
  static final ResourceBundle STRINGS = ResourceBundle.getBundle("view.gui.Language");

  public static final ComplexNumber ans = new ComplexNumber(); // final answer for the used
                                                               // operation

  /**
   * Adds the real and imaginary segments of a ComplexNumber object together and returns the sum.
   * 
   * @param numOne A complex number
   * @param numTwo A complex number
   * @return the sum of two complex numbers
   */
  public static ComplexNumber addition(ComplexNumber numOne, ComplexNumber numTwo) {
    double realAns = numOne.getRealNum() + numTwo.getRealNum();
    double imagAns = numOne.getImagNum() + numTwo.getImagNum();

    ans.setRealNum(realAns);
    ans.setImagNum(imagAns);
    return ans;

  }

  /**
   * Subtracts the real and imaginary segments of a ComplexNumber object together and returns the
   * answer.
   * 
   * @param numOne A complex number
   * @param numTwo A complex number
   * @return The difference of two complex numbers
   */
  public static ComplexNumber subtraction(ComplexNumber numOne, ComplexNumber numTwo) {
    double realAns = numOne.getRealNum() - numTwo.getRealNum();

    double imagAns = numOne.getImagNum() - numTwo.getImagNum();
    ans.setRealNum(realAns);
    ans.setImagNum(imagAns);
    return ans;
  }

  /**
   * Multiplies the real and imaginary segments of a ComplexNumber object together and returns the
   * Product. The FOIL Method for multiplying two binomials was used( Multiply First operands, Outer
   * operands, Inner operands, and Last operands). (a+ bi)(c + di)
   * 
   * @param numOne A complex number
   * @param numTwo A complex number
   * @return the product of two complex numbers
   */
  public static ComplexNumber multiplication(ComplexNumber numOne, ComplexNumber numTwo) {
    double Fprod = numOne.getRealNum() * numTwo.getRealNum(); // a * c
    double Iprod = numOne.getImagNum() * numTwo.getRealNum(); // bi * c
    double Oprod = numOne.getRealNum() * numTwo.getImagNum(); // a * di
    double Lprod = -(numOne.getImagNum() * numTwo.getImagNum()); // bi * di. sign is flipped to
                                                                 // account for i^2

    ans.setRealNum(Fprod + Lprod);
    ans.setImagNum(Iprod + Oprod);
    return ans;
  }

  /**
   * Divides two complex numbers by multiplying the numerator and denominator by the conjugate of
   * the second complex number(denominator) then dividing the new complex number numerator by the
   * new real number denominator.
   * 
   * @param numOne The initial numerator
   * @param numTwo The initial denominator
   * @return the quotient of two complex numbers
   * @throws DivideByZeroException thrown if numTwo(denominator) is equal to zero)
   */
  public static ComplexNumber division(ComplexNumber numOne, ComplexNumber numTwo)
      throws DivideByZeroException {
    if (numTwo.getImagNum() == 0 && numTwo.getRealNum() == 0)
      throw new DivideByZeroException(STRINGS.getString("CANNOT_DIVIDE_BY_ZERO"));

    ComplexNumber conjugate = new ComplexNumber(numTwo.getRealNum(), -numTwo.getImagNum());
    ComplexNumber numerator = multiplication(numOne, conjugate);
    double demoninator = (numTwo.getRealNum() * conjugate.getRealNum())
        + (-(numTwo.getImagNum() * conjugate.getImagNum()));

    ans.setRealNum(numerator.getRealNum() / demoninator);
    ans.setImagNum(numerator.getImagNum() / demoninator);
    return ans;
  }
  
  public static ComplexNumber power(ComplexNumber numOne, double exp) 
  {
    double modulus = Math.sqrt(numOne.getRealNum()*numOne.getRealNum() + numOne.getImagNum()*numOne.getImagNum());
    double arg = Math.atan2(numOne.getImagNum(),numOne.getRealNum());
    double log_re = Math.log(modulus);
    double log_im = arg;
    double x_log_re = exp * log_re;
    double x_log_im = exp * log_im;
    double modulus_ans = Math.exp(x_log_re);
    return new ComplexNumber(modulus_ans*Math.cos(x_log_im), modulus_ans*Math.sin(x_log_im));
    
  }
  public static ComplexNumber sqrt(ComplexNumber numOne) 
  {
    double dX, dY, dW, dR;
    
    if(numOne.getRealNum() == 0.0 && numOne.getImagNum() == 0.0)
      return numOne;
    
    dX = Math.abs(numOne.getRealNum());
    dY = Math.abs(numOne.getImagNum());
    
    if(dX >= dY)
    {
      dR = dY/dX;
      dW = Math.sqrt(dX) * Math.sqrt(0.5*(1.0+ Math.sqrt(1+dR*dR)));
    }else {
      dR = dX/dY;
      dW = Math.sqrt(dY) * Math.sqrt(0.5*(dR + Math.sqrt(1+dR*dR)));
    }
    
    double imagAns;
    if(numOne.getRealNum() >= 0.0) {
      ans.setRealNum(dW);
      ans.setImagNum(numOne.getImagNum()/( 2.0 * dW)); 
    }
    else
    {
      imagAns = (numOne.getImagNum() > 0.0) ? dW : -dW;
      ans.setImagNum(imagAns);
      ans.setRealNum(numOne.getImagNum()/ (2.0 *imagAns));
    }
    
    return ans;
  }
}
