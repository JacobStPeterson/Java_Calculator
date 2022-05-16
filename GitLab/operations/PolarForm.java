package operations;

import java.text.DecimalFormat;

public class PolarForm extends ComplexNumber
{
  private double r;
  private double theta;
  private DecimalFormat df = new DecimalFormat("#.###");

  public PolarForm(double realNum, double imagNum) {
    super(realNum, imagNum);
    this.r = Math.sqrt((realNum*realNum) + (imagNum * imagNum));
    if (realNum == 0) 
    {
      if(imagNum < 0) {
        this.theta = Math.PI * (3.0/2);
      }
      else if(imagNum > 0) {
        this.theta = Math.PI * (1.0/2);
      }
      else {
        this.theta = 0;
      }
    }
    else 
    {
      this.theta = Math.atan2(imagNum, realNum);
      if(realNum < 0)
        theta += Math.PI;
    }
  }
  
  public double getR() 
  {
    return r;
  }
  
  public double getTheta() 
  {
    return theta;
  }
  public void setR(double r) 
  {
    this.r = r;
  }
  public void setTheta(double theta) 
  {
    this.theta = theta;
  }
  public String toString() {
    
    if( r < 0)
      return df.format(r) + "Cos(" + df.format(theta) + ") - " +  df.format(Math.abs(r))
      + "Sin(" + df.format(theta) + ")i";
    else
      return df.format(r) + "Cos(" + df.format(theta) + ") + " +  df.format(r)
      + "Sin(" + df.format(theta) + ")i";
  }
}
