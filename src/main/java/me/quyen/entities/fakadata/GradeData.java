package me.quyen.entities.fakadata;

import java.util.concurrent.ThreadLocalRandom;

public class GradeData {
    public static String gradeName(){
        return gradeLevelAndCode()[0];
    }
    public static String gradeCode(){
        return gradeLevelAndCode()[1];
    }
    private static String[] gradeLevelAndCode(){
        String level = gradeLevels()[
                ThreadLocalRandom.current().nextInt(0,gradeLevels().length)
                ];
        String code = gradeCode(level);
        return new String[]{level,code};
    }
    private static String gradeCode(String gradeLevel){
        return switch (gradeLevel){
            case "EXCELLENT" -> "E";
            case "BETTER-POSSIBLE" -> "A";
            case "ABOVE-AVERAGE" -> "B";
            case "PASS" -> "C";
            case "AWARDED-FAIL" -> "D";
            case "NO-AWARD" -> "F";
            default -> "NO_ACTIVE";
        };
    }
   public static String[] gradeLevels(){
       return new String[]{
               "EXCELLENT",
               "BETTER-POSSIBLE",
               "ABOVE-AVERAGE","PASS",
               "AWARDED-FAIL","NO-AWARD"
       };
   }
}
