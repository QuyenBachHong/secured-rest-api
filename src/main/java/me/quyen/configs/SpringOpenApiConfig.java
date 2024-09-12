package me.quyen.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringOpenApiConfig {
    /**
     * http://server:port/context-path/v3/api-docs/GROUP_NAME
     * this case, GROUP_NAME = 'students-books
     * then: http://localhost:8080/v3/api-docs/student-book-group
     */
    @Bean
    public org.springdoc.core.models.GroupedOpenApi studentGroup() {
        String[] paths = {"/api/students/**"};
        String[] packagesToScan = {
                "me.quyen.controllers.studentbook.student"
        };
        return org.springdoc.core.models.GroupedOpenApi.builder()
                .group("student-group")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }
    @Bean
    public org.springdoc.core.models.GroupedOpenApi bookGroup() {
        String[] paths = {"/api/books/**"};
        String[] packagesToScan = {
                "me.quyen.controllers.studentbook.book"
        };
        return org.springdoc.core.models.GroupedOpenApi.builder()
                .group("book-group")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }
    @Bean
    public org.springdoc.core.models.GroupedOpenApi teacherGroup() {
        String[] paths = {"/api/teachers/**"};
        String[] packagesToScan = {
                "me.quyen.controllers.studentbook.teacher"
        };
        return org.springdoc.core.models.GroupedOpenApi.builder()
                .group("teacher-group")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }

}
