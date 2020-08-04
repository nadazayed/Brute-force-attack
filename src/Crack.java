import javax.crypto.*;
import java.security.*;
import 	javax.crypto.spec.SecretKeySpec;

public class Crack
{
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException
    {
        // Generate key for DES
        byte[] key = ("11111114").getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"DES");

        // Message encryption/ decryption
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        // Encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        String plain = "nadazayed";
        byte []message = plain.getBytes();
        byte []enc = cipher.doFinal(message);
        String encMsg = new String(enc);
        System.out.println("Cipher text: "+new String(enc));

        ////////////////////////////////CRACK/////////////////////////////////
        byte[] BFAkey ;
        byte []BFAdec;
        String BFAdecMsg;
        boolean flag = false;
        SecretKeySpec secretKeySpec2;
        StringBuilder temp = new StringBuilder();

        for (int i1=1; i1<=8 && !flag; i1++)
        {
            for (int i2=1; i2<=8 && !flag; i2++)
            {
                for (int i3=1; i3<=8 && !flag; i3++)
                {
                    for (int i4=1; i4<=8 && !flag; i4++)
                    {
                        for (int i5=1; i5<=8 && !flag; i5++)
                        {
                            for (int i6=1; i6<=8 && !flag; i6++)
                            {
                                for (int i7=1; i7<=8 && !flag; i7++)
                                {
                                    for (int i8=1; i8<=8 && flag == false; i8++)
                                    {
                                        BFAkey = (i1+""+i2+""+i3+""+i4+""+i5+""+i6+""+i7+""+i8).getBytes(); //1111111  11111112 11111113 > 11111118
                                        //System.out.println(i1+""+i2+""+i3+""+i4+""+i5+""+i6+""+i7+""+i8);
                                        //BFAkey = "11111116".getBytes();
                                        secretKeySpec2 = new SecretKeySpec(BFAkey,"DES");
                                        cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
                                        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec2);

                                        try
                                        {
                                            BFAdec = cipher.doFinal(enc);
                                            BFAdecMsg = new String(BFAdec);
                                            //System.out.println("key: "+new String(BFAkey)+" plain text: "+BFAdecMsg);

                                            if (BFAdecMsg.matches(plain))
                                            {
                                                System.out.println("Found Match! plain text: "+plain+" cipher text: "+BFAdecMsg+" key: "+new String(BFAkey));
                                                flag = true;
                                                break;
                                            }
                                            else
                                                System.out.println("Doesn't match\n");
                                        }

                                        catch (BadPaddingException e)
                                        {
                                            //
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
