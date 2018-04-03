import java.util.*;
import java.io.*;
import static java.lang.System.*;

class Encrypt
{
   static String abc[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
						        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
   static int count = 0;

   /**
   *The encrypt method encrypts then writes a message to the output.txt using a rudimentary method of taking the
   *original characters and replacing them with characters who's indices are 8 away from the original in the English alphabet.
   *
   *String message is the message being encrypted.
   *int upDown 1 instructs the method to shift indices to the right, whereas 0 instructs a left shift.
   *int loop specifies how many times the message must be encrypted.
   *returns either encrypted message plus loop # and, if required, recursively called ecrypt method.
   */
   public static String encrypt(String message, int upDown, int loop)
   {
      String retMessage = "";
      int shift = 0;
      if (upDown > 0)
      {
         shift = 8;
      }
      else
      {
         shift = -8;
      }
      for (int i = 0; i < message.length(); i++)
      {
         int index = 0;
         if (message.substring(i, i+1).equals(" "))
         {
            retMessage += " ";
         }
         else
         {
            while (index < 26)
            {
               if (message.substring(i, i+1).equals(abc[index]))
               {
                  break;
               }
               index++;
            }
            index += shift;
            if (index >= 26)
            {
               index -= 26;
            }
            else if (index < 0)
            {
               index += 26;
            }
            retMessage += abc[index];
         }
      }
      count++;
      if (loop == 1)
      {
         out.println(count);
         return retMessage + " loop#" + count + "\n";
      }
      else
      {
         out.println(count);
         return (retMessage + " loop#" + count + "\n") + encrypt(retMessage, upDown, loop-1);
      }
   }
	
   public static void main(String args[])throws IOException
   {
      Scanner inData = new Scanner(new File("data.dat"));
      String mes = inData.nextLine();
      int ud = inData.nextInt();
      int l = inData.nextInt();
      inData.close();
      BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
      writer.write(encrypt(mes, ud, l));
      writer.close();
   }
}