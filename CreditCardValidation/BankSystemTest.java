import java.util.*;

/**
 * Created by rodrigo on 4/18/16.
 */
public class BankSystemTest {


    public static void main(String[] args) {
        banktest();
        integrationbanktest();

    }
    //method where all my tests are in. copy over to main test class
    public static void banktest(){

        //Mastercard type check
        System.out.println("~~~~ correctTypeMasterCard Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : Valid MasterCard Type");
        System.out.print("***System Response : ");
        BankSystem bank = new BankSystem("mastercard","1/2016","4556737586899855");
        boolean temp = bank.CorrectType();
        if(temp==true)
            System.out.println("Valid MasterCard Type");
        else
            System.out.println("name: Not valid type");

        System.out.println("\n");

        //Visa type check
        System.out.println("~~~~ correctTypeVisa Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : Valid Visa Type");
        System.out.print("***System Response : ");
        bank = new BankSystem("visa","1/2016","4556737586899855");
        temp = bank.CorrectType();
        if(temp==true)
            System.out.println("Valid Visa Type");
        else
            System.out.println("name: Not valid type");

        System.out.println("\n");
        //Discover type check
        System.out.println("~~~~ correctTypeDiscover Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : Valid Discover Type");
        System.out.print("***System Response : ");
        bank = new BankSystem("discover","1/2016","4556737586899855");
        temp = bank.CorrectType();
        if(temp==true)
            System.out.println("Valid Discover Type");
        else
            System.out.println("name: Not valid type");

        System.out.println("\n");

        //american express check
        System.out.println("~~~~ correctTypeAmericanExpress Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : Valid American Express Type");
        System.out.print("***System Response : ");
        bank = new BankSystem("american express","1/2016","4556737586899855");
        temp = bank.CorrectType();
        if(temp==true)
            System.out.println("Valid American Express Type");
        else
            System.out.println("name: Not valid type");

        System.out.println("\n");

        //expiration date is in the future
        System.out.println("~~~~ validDate Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : Valid Expiration Date");
        System.out.print("***System Response : ");
        bank = new BankSystem("visa","7/2016","4556737586899855");
        temp = bank.validDate();
        if(temp==true)
            System.out.println("Valid Expiration Date");
        else
            System.out.println("Card Expired");

        System.out.println("\n");

        //expired card
        System.out.println("~~~~ validDateExpired Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : Card Expired");
        System.out.print("***System Response : ");
        bank = new BankSystem("visa","1/2016","4556737586899855");
        temp = bank.validDate();
        if(temp==true)
            System.out.println("Valid Expiration Date");
        else
            System.out.println("Card Expired");

        System.out.println("\n");

        //correct/valid card number passed through
        System.out.println("~~~~ ValidCardNumber Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : Valid Card Number");
        System.out.print("***System Response : ");
        bank = new BankSystem("visa","1/2016","4556737586899855");
        temp = bank.CorrectNum();
        if(temp==true)
            System.out.println("Valid Card Number");
        else
            System.out.println("Invalid Card Number");

        System.out.println("\n");

        //incorrect/invalid card numbered passed through.
        System.out.println("~~~~ validCardNumFail Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : Invalid Card Number");
        System.out.print("***System Response : ");
        bank = new BankSystem("visa","1/2016","4556737586899853");
        temp = bank.CorrectNum();
        if(temp==true)
            System.out.println("Valid Card Number");
        else
            System.out.println("Invalid Card Number");

        System.out.println("\n");

        //should succeed, valid card - code 4
        System.out.println("~~~~ ValidMethodPass Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : valid = code 4");
        System.out.print("***System Response : ");
        bank = new BankSystem("visa","10/2016","4556737586899855");
        int value = bank.valid();
        System.out.println("valid = code "+value);
        System.out.println("\n");

        //checks valid type name - error code 1
        System.out.println("~~~~ ValidMethodFailError1 Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : valid = code 1");
        System.out.print("***System Response : ");
        bank = new BankSystem("visaa","10/2016","4556737586899855");
        value = bank.valid();
        System.out.println("valid = code "+value);
        System.out.println("\n");

        //checks valid date - error code 2
        System.out.println("~~~~ ValidMethodFailError2 Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : valid = code 2");
        System.out.print("***System Response : ");
        bank = new BankSystem("visa","1/2016","4556737586899855");
        value = bank.valid();
        System.out.println("valid = code "+value);
        System.out.println("\n");

        //checks valid number - error code 3
        System.out.println("~~~~ ValidMethodFailError3 Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : valid = code 3");
        System.out.print("***System Response : ");
        bank = new BankSystem("visa","10/2016","4556737586899853");
        value = bank.valid();
        System.out.println("valid = code "+value);
        System.out.println("\n");
    }

    public static void integrationbanktest(){
        BankSystem bank;
        FrameWorkController.initialize("cardinegration.txt");
        System.out.println("~~~~ Integration valid card Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : Valid Card");
        System.out.print("***System Response : ");
        Customer temp1 = Framework.getCustomerByName("Rodrigo S. Jaldin");
        bank = new BankSystem(temp1.getCCType(),temp1.getCCExpiration(),temp1.getCCNumber());
        //if(temp1.getCCType().equals("Visa")==true && temp1.getCCExpiration().equals("10/2018")== true &&
        // temp1.getCCNumber().equals("4716001921994146670")==true)
        if(bank.valid()==4)
                System.out.println("Valid Card");
        else
                System.out.println("INVALID CARD");

        System.out.println("~~~~ Integration invalid card Test~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("***Expected result : Valid Card");
        System.out.print("***System Response : ");
        FrameWorkController.initialize("cardinegration.txt");
        temp1 = Framework.getCustomerByName("Bernard L. Silver");
        bank = new BankSystem(temp1.getCCType(),temp1.getCCExpiration(),temp1.getCCNumber());
        //if(temp1.getCCType().equals("Visa")==true && temp1.getCCExpiration().equals("10/2018")== true &&
        // temp1.getCCNumber().equals("4716001921994146670")==true)
        if(bank.valid()==4)
            System.out.println("Valid Card");
        else
            System.out.println("INVALID CARD");
    }

}