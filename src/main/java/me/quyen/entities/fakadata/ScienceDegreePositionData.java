package me.quyen.entities.fakadata;

import java.util.concurrent.ThreadLocalRandom;

public class ScienceDegreePositionData {
    public static String scienceDegreeName(){
        return positionsAndAbbreviates()[0];
    }
    public static String scienceDegreeCode(){
        return positionsAndAbbreviates()[1];
    }
    private static String[] positionsAndAbbreviates(){
        int rp = ThreadLocalRandom.current()
                .nextInt(0,sciencePositions().length);
        int rg = ThreadLocalRandom.current()
                .nextInt(0,scienceDegrees().length);
        String sd = scienceDegrees()[rg].substring(0,2).toUpperCase();
        String sp = positionAbr(sciencePositions()[rp]);
        int ri = ThreadLocalRandom.current().nextInt(100, 999);
        String res = sd + sp + ri;
        return  new String[]{
                scienceDegrees()[rg] + " " + sciencePositions()[rp]
                ,res
        };
    }
    private static String positionAbr(String position){
        return switch (position) {
            case "Postgraduate" -> "-PGR";
            case "Bachelor" -> "-BCL";
            case "Master" -> "-MAS";
            case "Doctor" -> "-DOC";
            case "Professor" -> "-PRO";
            case "Specialist" -> "-SPL";
            case "PostDoctor" -> "-PDC";
            default -> "-SNR";
        };
    }
    public static String[] sciencePositions(){
        return new String[]{
                "Postgraduate", "Bachelor", "Master",
                "Doctor", "Professor", "Assistant Professor",
                "Specialist", "PostDoctor"
        };
    }
    public static String[] scienceDegrees(){
        return new String[]{
                "Anthropology",
                "Agriculture",
                "Biology",
                "Computer Science",
                "Microbiology",
                "Mathematics",
                "Psychology",
                "Statistics",
                "Biochemistry",
                "Biotechnology",
                "Botany",
                "Economics",
                "Physics",
                "Environmental science",
                "Nursing",
                "Programming",
                "Geology",
                "Industrial Chemistry",
                "Zoology",
                "Chemistry",
                "Chemistry",
                "IT",
                "Genetics",
                "Surgery",
                "Medicine",
                "Civil Law",
                "Philosophy",
                "Business Sciences",
                "Architecture",
                "Advanced Study",
                "Business Administration",
                "Business and Law",
                "linical DentistryDesign",
                "Education",
                "Engineering",
                "Educational Practice",
                "Fine Art",
                "Health and Wellbeing",
                "Librarianship",
                "Literature",
                "Public Administration",
                "Public Policy",
                "Professional Studies",
                "Research",
                "Social Studies",
                "Dental Science",
                "Social Work",
                "Theology"
        };
    }
}
