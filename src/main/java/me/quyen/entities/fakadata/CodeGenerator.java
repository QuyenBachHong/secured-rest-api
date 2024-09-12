package me.quyen.entities.fakadata;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class CodeGenerator {
    private static final String[] letters = new String[]{
            "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };
    private static final String[] numbers = new String[]{
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"
    };
    public static String code(int letterLength, int numberLength){
        if (letterLength <= 0 || numberLength <= 0 ){
            throw new IllegalArgumentException("both arguments `letterLength` and `numberLength` must be greater than 0!");
        }
        int lInt = ThreadLocalRandom.current().nextInt(0,letters.length);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < letterLength-1; i++){
            res.append(letters[ThreadLocalRandom.current().nextInt(0, letters.length)]);
        }
        res.append("-");
        for (int i = 0; i < letterLength-1; i++){
            res.append(ThreadLocalRandom.current().nextInt(0,numbers.length));
        }
        return res.toString();
    }

    public static LocalDate randomEstablishedDate(){
        int year = ThreadLocalRandom.current().nextInt(1940,2010);
        int month = ThreadLocalRandom.current().nextInt(1,13);
        int dayOfMonth;
        if(month==2){
            dayOfMonth = ThreadLocalRandom.current().nextInt(1,28);
        }else if((month == 4) ||(month == 6)||(month == 9)||(month == 11)){
            dayOfMonth = ThreadLocalRandom.current().nextInt(1,30);
        }else{
            dayOfMonth = ThreadLocalRandom.current().nextInt(1,31);
        }
        return LocalDate.of(year,month,dayOfMonth);
    }
    public static LocalDate randomBirthday(){
        int year = ThreadLocalRandom.current().nextInt(1940,2006);
        int month = ThreadLocalRandom.current().nextInt(1,13);
        int dayOfMonth;
        if(month==2){
            dayOfMonth = ThreadLocalRandom.current().nextInt(1,28);
        }else if((month == 4) ||(month == 6)||(month == 9)||(month == 11)){
            dayOfMonth = ThreadLocalRandom.current().nextInt(1,30);
        }else{
            dayOfMonth = ThreadLocalRandom.current().nextInt(1,31);
        }
        return LocalDate.of(year,month,dayOfMonth);
    }
    public String emailAddress(String name){
        Objects.requireNonNull(name);
        String[] mailTails
                = new String[]{
                        "@gmail.com","@outlook.com","@email.com"
                        ,"@live.com","@hotmail.com","@email.org"
                        ,"@msn.com","@yahoo.com", "@email.me","@example.com"
                };
        int rInt = ThreadLocalRandom.current().nextInt(0,mailTails.length);
        String mailTail = mailTails[rInt];
        return name.trim().replace(" ",".").toLowerCase()
                .concat(mailTail);
    }
}
