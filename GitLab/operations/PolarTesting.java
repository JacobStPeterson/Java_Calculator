package operations;

public class PolarTesting
{

  public static void main(String[] args)
  {
    ComplexNumber test = new ComplexNumber(0,2);
    
    PolarForm polarTest = new PolarForm(test.getRealNum(),test.getImagNum());
    System.out.println(polarTest.getImagNum());
    System.out.println(polarTest.getR());
    System.out.println(polarTest.getTheta());
    System.out.println(polarTest.toString());
  }

}
 