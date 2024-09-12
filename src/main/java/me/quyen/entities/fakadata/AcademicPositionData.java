package me.quyen.entities.fakadata;

import java.util.concurrent.ThreadLocalRandom;

public class AcademicPositionData {
    public static String randomPosition(){
        return positions()[
                ThreadLocalRandom.current().nextInt(0,positions().length)
                ];
    }
    private static String[] positions(){
        return new String[]{
                "SENIOR-TUTOR", "PROFESSORIAL-TEACHING-FELLOW", "SENIOR-TEACHING-FELLOW",
                "TEACHING-FELLOW", "TEACHING-ASSOCIATE", "PRINCIPAL-TECHNOLOGIST",
                "DEPARTMENTAL-MANAGER", "DEPARTMENTAL-COORDINATOR", "SENIOR-CHIEF-TECHNOLOGIST",
                "LAB-MANAGER", "LAB-COORDINATOR", "CHIEF-TECHNICIAN",
                "SENIOR-TECHNICIAN", "ADVANCED-TECHNICIAN", "TECHNICIAN",
                "JUNIOR-TECHNICIAN", "APPRENTICE-TECHNICIAN", "TRAINEE-TECHNICIAN",
                "SENIOR RESEARCH FELLOW", "SENIOR RESEARCHER",
                "RESEARCH FELLOW, RESEARCH ASSOCIATE", "RESEARCH-ASSISTANT",
                "PROFESSOR", "SENIOR-LECTURER", "LECTURER", "CLINICAL-ECTURER",
                "ASSISTANT-LECTURER", "DEMONSTRATOR", "SEMINAR-LEADER", "ASSOCIATE-LECTURER",
                "GRADUATE-TEACHING-ASSISTANT", "DEPARTMENTAL-LECTURE",
                "SENIOR-PROFESSOR", "ASSOCIATE-PROFESSOR", "ASSISTANT-PROFESSOR",
                "SCIENTIST-H", "SCIENTIST-G", "SCIENTIST-F", "SCIENTIST-E",
                "SCIENTIST-D", "SCIENTIST-C", "SCIENTIST-B", "BACHELOR",
                "PHILOSOPHY-DOCTOR", "MAGISTER",
                "PHILOSOPHY-DOCTOR-SENIOR", "MAGISTER-SENIOR"
                ,"PHILOSOPHY-DOCTOR-ASSOCIATE", "MAGISTER-ASSOCIATE",
                "PHILOSOPHY-DOCTOR-ASSISTANT", "MAGISTER-ASSISTANT"

        };
    }
}
