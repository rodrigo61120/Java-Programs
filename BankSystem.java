/**
 * Created by rodrigo on 4/9/16.
 *
 * This is the Bank validation system for the Hotel System
 *
 * example of how to use this class
 *
 * BankSystem temp = new BankSystem("Visa", "4/2016","4556737586899855");
 * int validation = temp.Valid();           //validation now holds error or validation value
 *
 *
 * valid returns
 *      1 - error : if cardtype is not valid. ex-"bisa"
 *      2 - error : if date is expired
 *      3 - error : if card number is not valid. based on luhn formula
 *      4 - valid card.
 */
import java.util.*;

    public class BankSystem {

        private String CardNum = "";  //card number
        private String CardType="";   //card type
        private String expires ="";     //expiration date

        //constructor. setus up variables
        public BankSystem(String CardType, String expires, String CardNum) {
            this.CardNum = CardNum;
            this.CardType = CardType;
            this.expires = expires;
        }

        //l
        //returns false if not valid card type
        //private boolean CorrectType(String CardType) {
        public boolean CorrectType() {
            String[] validtypes = {"Visa", "MasterCard", "American Express", "Discover"};
            boolean sameType = false;
            int i=0;
            while(sameType==false&&i<4) {
                sameType = CardType.equalsIgnoreCase(validtypes[i]);
                i++;
            }
            return sameType;
        }
        //checks to see if the date given is a valid date or an expired date.
        //returns false if not valid date.
        //private boolean validDate(String date){
        public boolean validDate(){
            String[] split = expires.split("/");
            int CardMonth = Integer.parseInt(split[0]);
            int CardYear = Integer.parseInt(split[1]);
            Calendar cal = Calendar.getInstance();
            int TodayMonth = cal.get(cal.MONTH);
            int TodayYear = cal.get(cal.YEAR);

            if(CardYear>TodayYear)
                return true;
            else if(CardYear==TodayYear&&CardMonth>=TodayMonth+1)
                return true; //+1 because Calendar starts month at 0=january

            return false;

        }
        //checks to see if the given card number is valid using the luhn formula
       // private boolean CorrectNum(String CardNumString) {
        public boolean CorrectNum() {
            //splits the string onto an array of strings. each character at each index
            //converts the character into string and save it inside an integer array
            String[] split = CardNum.split("");
            int[] num = new int[split.length];

            for (int i = 0; i < split.length; i++) {
                num[i] = Integer.parseInt(split[i]);
            }

            if()


            //Luhn formula on the integer array.
            int lastNum = num[num.length - 1];
            for (int i = num.length - 2; i >= 0; i--) {
                if (i % 2 == 0)                //odd index multiply times 2. index 0 = odd #1
                    num[i] = num[i] * 2;
            }
            for (int i = num.length - 2; i >= 0; i--) { //subtract 9 form numbers greater than 9
                if (num[i] > 9)
                    num[i] = num[i] - 9;
            }
            int sum = 0;
            for (int i = num.length - 2; i >= 0; i--) { //add all numbers
                sum = num[i] + sum;
            }
            int mod = sum % 10;

            if(mod==lastNum) return true;

            return false;
        }

        public int valid(){
            //returns 1 if not valid type name
            //returns 2 if not valid date
            //returns 3 if not valid card number
            //returns 4 if it is a valid card

            if(CorrectType()==false)
                return 1;
            if(validDate()==false)
                return 2;
            if(CorrectNum()==false)
                return 3;

            return 4;

        }

    }