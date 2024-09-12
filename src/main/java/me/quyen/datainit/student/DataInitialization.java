package me.quyen.datainit.student;

import lombok.RequiredArgsConstructor;
import me.quyen.entities.student.*;
import me.quyen.entities.student.book.*;
import me.quyen.repositories.student.*;
import me.quyen.repositories.student.book.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@Component
@RequiredArgsConstructor
public class DataInitialization {
    final UniversityRepo universityRepo;
    final FacultyRepo facultyRepo;
    final DepartmentRepo departmentRepo;
    final AuthorRepo authorRepo;
    final BookRepo bookRepo;
    final BookAuthorRepo bookAuthorRepo;
    final LibraryRepo libraryRepo;
    final PublisherRepo publisherRepo;
    final BookLibraryRepo bookLibraryRepo;
    final BookPublisherRepo bookPublisherRepo;
    final CourseRepo courseRepo;
    final StudentBookRepo studentBookRepo;
    final StudentCourseRepo studentCourseRepo;
    final StudentRepo studentRepo;
    final StudentTeacherRepo studentTeacherRepo;
    final TeacherCourseRepo teacherCourseRepo;
    final TeacherRepo teacherRepo;
    final int UNIVERSITY_SIZE = 8, FACULTY_SIZE = 8, DEPARTMENT_SIZE = 16;
    final int AUTHOR_SIZE = 16, LIBRARY_SIZE = 16, PUBLISHER_SIZE = 16, BOOK_SIZE = 32;
    final int COURSE_SIZE = 16, TEACHER_SIZE = 16, STUDENT_SIZE = 32;

    @Bean
    @Transactional(transactionManager = "studentTransactionManager")
    public ApplicationRunner saveData(){
        return args -> {
            List<University> universities = IntStream.range(0, UNIVERSITY_SIZE).boxed()
                    .map(i -> University.of()).collect(Collectors.toList());
            universityRepo.saveAll(universities);
            List<Faculty> faculties = IntStream.range(0, FACULTY_SIZE).boxed()
                    .map(i -> Faculty.of(universities.get(rand(0,UNIVERSITY_SIZE))))
                    .collect(Collectors.toList());
            facultyRepo.saveAll(faculties);
            List<Department> departments = IntStream.range(0, DEPARTMENT_SIZE).boxed()
                    .map(i -> Department.of(faculties.get(rand(0,FACULTY_SIZE))))
                    .collect(Collectors.toList());
            departmentRepo.saveAll(departments);
            // ##############################################################
            List<Author> authors = IntStream.range(0, AUTHOR_SIZE).boxed()
                    .map(i -> Author.of()).collect(Collectors.toList());
            authorRepo.saveAll(authors);
            List<Library> libraries = IntStream.range(0, LIBRARY_SIZE).boxed()
                    .map(i -> Library.of()).collect(Collectors.toList());
            libraryRepo.saveAll(libraries);
            List<Publisher> publishers = IntStream.range(0, PUBLISHER_SIZE).boxed()
                    .map(i -> Publisher.of()).collect(Collectors.toList());
            publisherRepo.saveAll(publishers);
            List<Book> books = IntStream.range(0, BOOK_SIZE).boxed()
                    .map(i -> Book.of()).collect(Collectors.toList());
            bookRepo.saveAll(books);


                bookAuthorRepo.saveAll(authors.stream().map(author -> {
                            Long id = author.getId();
                            return books.stream().filter(book -> (book.getId() % (AUTHOR_SIZE / 2)) == id)
                                    .map(book -> BookAuthor.of(book,author)).collect(Collectors.toList());
                        }).flatMap(Collection::stream).collect(Collectors.toList())
                );

                bookLibraryRepo.saveAll(libraries.stream().map(library -> {
                            Long id = library.getId();
                            return books.stream().filter(book -> (book.getId() % (LIBRARY_SIZE / 2)) == id)
                                    .map(book -> BookLibrary.of(book,library)).collect(Collectors.toList());
                        }).flatMap(Collection::stream).collect(Collectors.toList())
                );

                bookPublisherRepo.saveAll(publishers.stream().map(publisher -> {
                            Long id = publisher.getId();
                            return books.stream().filter(book -> (book.getId() % (PUBLISHER_SIZE / 2)) == id)
                                    .map(book -> BookPublisher.of(book,publisher)).collect(Collectors.toList());
                        }).flatMap(Collection::stream).collect(Collectors.toList())
                );

            //=========================================================
            List<Course> courses = IntStream.range(0, COURSE_SIZE).boxed()
                    .map(i -> Course.of()).collect(Collectors.toList());
            courseRepo.saveAll(courses);
            List<Teacher> teachers = IntStream.range(0, TEACHER_SIZE).boxed()
                    .map(i -> Teacher.of(departments.get(rand(0, DEPARTMENT_SIZE))
                    )).collect(Collectors.toList());
            teacherRepo.saveAll(teachers);
            List<Student> students = IntStream.range(0, STUDENT_SIZE).boxed()
                    .map(i -> Student.of(departments.get(rand(0, DEPARTMENT_SIZE))
                    )).collect(Collectors.toList());
            studentRepo.saveAll(students);


                teacherCourseRepo.saveAll(courses.stream().map(course -> {
                            Long id = course.getId();
                            return teachers.stream().filter(teacher -> (teacher.getId() % (COURSE_SIZE / 2)) == id)
                                    .map(teacher -> TeacherCourse.of(teacher,course)).collect(Collectors.toList());
                        }).flatMap(Collection::stream).collect(Collectors.toList())
                );

                studentBookRepo.saveAll(books.stream().map(book -> {
                            Long id = book.getId();
                            return students.stream().filter(student -> (student.getId() % (BOOK_SIZE / 2)) == id)
                                    .map(student -> StudentBook.of(student,book)).collect(Collectors.toList());
                        }).flatMap(Collection::stream).collect(Collectors.toList())
                );

                studentCourseRepo.saveAll(courses.stream().map(course -> {
                            Long id = course.getId();
                            return students.stream().filter(student -> (student.getId() % (COURSE_SIZE / 2)) == id)
                                    .map(student -> StudentCourse.of(student,course)).collect(Collectors.toList());
                        }).flatMap(Collection::stream).collect(Collectors.toList())
                );

                studentTeacherRepo.saveAll(teachers.stream().map(teacher -> {
                            Long id = teacher.getId();
                            return students.stream().filter(student -> (student.getId() % (TEACHER_SIZE / 2)) == id)
                                    .map(student -> StudentTeacher.of(student,teacher)).collect(Collectors.toList());
                        }).flatMap(Collection::stream).collect(Collectors.toList())
                );

        };//return args ->.............
    }

    private int rand(int min, int maxOut){
        return ThreadLocalRandom.current().nextInt(min,maxOut);
    }
}
