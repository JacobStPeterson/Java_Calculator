package operations;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Fraction
{
  public static int[] fractionConverter(String answer)
  {
    String[] pieces = null;
    int[] numDen = null;
    BigDecimal number = new BigDecimal(answer);
    if (answer.toString().contains("."))
    {
      pieces = number.toString().split("\\.");
    }
    else
    {
      numDen[0] = Integer.parseInt(answer);
      numDen[1] = 1;
      return numDen;
    }

    BigDecimal denom = BigDecimal.TEN.pow(pieces[1].length());
    BigDecimal numer = (new BigDecimal(pieces[0]).multiply(denom)).add(new BigDecimal(pieces[1]));

    return reduce(numer.intValue(), denom.intValue());
  }

  public static int[] reduce(int numer, int denom)
  {
    int gcd = BigInteger.valueOf(numer).gcd(BigInteger.valueOf(denom)).intValue();
    int[] numDen = {numer / gcd, denom / gcd};
    return numDen;
  }
}
