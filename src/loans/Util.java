package loans;

import java.net.URLEncoder;
import java.sql.SQLWarning;
import java.sql.SQLException;
import java.text.*;
import java.util.*;


public class Util
{
  /***************************************************************************
   *  Constants                                                              *
   ***************************************************************************/

  public static DecimalFormat    csd2 = new DecimalFormat ("#,##0.00");
  public static DecimalFormat    uscur2 = new DecimalFormat ("$#,##0.00");
  public static SimpleDateFormat dateMDY = new SimpleDateFormat ("MM/dd/yyyy");
  public static DecimalFormat    dec2 = new DecimalFormat ("#0.00");
  public static DecimalFormat    dec3 = new DecimalFormat ("#0.000");
  public static SimpleDateFormat dtMDYHM = new SimpleDateFormat ("MM/dd/yyyy HH:mm");
  public static SimpleDateFormat dtMDYHMS = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
  public static DecimalFormat    ordNoFmt = new DecimalFormat ("000000");
  public static SimpleDateFormat timeHM = new SimpleDateFormat ("HH:mm");
  public static SimpleDateFormat timeHMS = new SimpleDateFormat ("HH:mm:ss");


  /***************************************************************************
   *  sqlChar                                                                *
   ***************************************************************************/

  public static String sqlChar (char chr)
    {
      if (chr == '\'')
          return "''''";
        else
          return "'" + chr + "'";
    }


  /***************************************************************************
   *  sqlDate                                                                *
   ***************************************************************************/

  public static String sqlDate (Date date)
    {
      return (date == null) ? "null" : "'" + dateMDY.format (date) + "'";
    }


  /***************************************************************************
   *  sqlDateTime                                                            *
   ***************************************************************************/

  public static String sqlDateTime (Date dt)
    {
      return (dt == null) ? "null" : "'" + dtMDYHMS.format (dt) + "'";
    }


  /***************************************************************************
   *  sqlStr                                                                 *
   ***************************************************************************/

  public static String sqlStr (String str)
    {
      String newStr = "'";
      for (int idx = 0; idx < str.length (); idx++)
        {
          newStr += str.charAt (idx);
          if (str.charAt (idx) == '\'')
              newStr += "'";
        }
      newStr += "'";
      return newStr;
    }

  public static String rateStr (String str) {
      String newStr = ".";
      for (int idx = 0; idx <str.length(); idx++) {
          if(str.charAt(idx) == '.')
             newStr += "";
          else
              newStr += str.charAt(idx);
      }

      return newStr;
  }
}