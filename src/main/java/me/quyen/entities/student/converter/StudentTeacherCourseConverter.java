package me.quyen.entities.student.converter;

import me.quyen.entities.student.*;
import me.quyen.entities.student.book.Book;
import me.quyen.entities.student.dtos.BookDto;
import me.quyen.entities.student.dtos.CourseDto;
import me.quyen.entities.student.dtos.StudentDto;
import me.quyen.entities.student.dtos.TeacherDto;
import me.quyen.repositories.student.*;
import me.quyen.repositories.student.book.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentTeacherCourseConverter {
    public static CourseDto courseDto(Course course){
        return Optional.ofNullable(course)
                .stream().map(src -> {
                    CourseDto dsc = new CourseDto();
                    BeanUtils.copyProperties(src,dsc);
                    return dsc;
                }).findFirst().orElse(null);
    }
    public static Course course(CourseDto courseDto){
        return Optional.ofNullable(courseDto).stream().map(src -> {
            Course dsc = new Course();
            BeanUtils.copyProperties(src,dsc);
            return dsc;
        }).findFirst().orElse(null);
    }
    public static TeacherDto teacherDto(Teacher teacher,DepartmentRepo departmentRepo,CourseRepo courseRepo){
        return Optional.ofNullable(teacher)
                .stream().map(src -> {
                    TeacherDto dsc = new TeacherDto();
                    BeanUtils.copyProperties(src,dsc);
                    dsc.setAddress(AddressConverter.addressDto(src.getAddress()));
                    Optional.ofNullable(src.getPersonalData()).ifPresent(personalData ->
                            dsc.setPersonalData(PersonalDataConverter.personalDataDto(personalData))
                    );

                    Optional.ofNullable(src.getDepartment()).ifPresent(department -> {
                        var departmentById = departmentRepo.findById(department.getId()).orElse(null);
                        dsc.setDepartment(UniversityFacultyDepartmentConverter.departmentDto(departmentById));
                    });
                    dsc.getCourses().addAll(
                            courseRepo.findAllById(
                                        src.getTeacherCourses().stream().map(TeacherCourse::getCourse).map(Course::getId)
                                            .collect(Collectors.toList())
                            ).parallelStream()
                              .map(StudentTeacherCourseConverter::courseDto).toList()
                    );
                    return dsc;
                }).findFirst().orElse(null);
    }

    public static Teacher teacher(TeacherDto teacherDto
            , UniversityRepo universityRepo, FacultyRepo facultyRepo, DepartmentRepo departmentRepo
            , CourseRepo courseRepo, TeacherCourseRepo teacherCourseRepo, TeacherRepo teacherRepo){
        return Optional.ofNullable(teacherDto).stream().map(src -> {
            Teacher dsc = new Teacher();
            BeanUtils.copyProperties(src,dsc);
            dsc.setAddress(AddressConverter.address(src.getAddress()));
            Optional.ofNullable(src.getPersonalData()).ifPresent(personalDataDto -> {
                dsc.setPersonalData(PersonalDataConverter.personalData(personalDataDto));
            });
            Optional.ofNullable(src.getDepartment()).ifPresent(departmentDto -> {
                Department department = UniversityFacultyDepartmentConverter.department(departmentDto, universityRepo, facultyRepo);
                Optional.ofNullable(department).ifPresent(dept -> {
                    departmentRepo.save(dept);
                    dsc.addAssociation(dept);

                });
            });
            List<Course> courseList = src.getCourses().stream().map(StudentTeacherCourseConverter::course)
                    .collect(Collectors.toList());
            courseRepo.saveAll(courseList);
            teacherRepo.save(dsc);
            teacherCourseRepo.saveAll(courseList.stream().map(course -> TeacherCourse.of(dsc,course)).collect(Collectors.toList()));
            return dsc;
        }).findFirst().orElse(null);

    }

    public static StudentDto studentDto(Student studentEntity
            , BookRepo bookRepo, TeacherRepo teacherRepo,DepartmentRepo departmentRepo,CourseRepo courseRepo){
        return Optional.ofNullable(studentEntity)
                .stream().map(student -> {
                    StudentDto dsc = new StudentDto();
                    BeanUtils.copyProperties(student,dsc);
                    dsc.setAddress(AddressConverter.addressDto(student.getAddress()));
                    dsc.setPersonalData(PersonalDataConverter.personalDataDto(student.getPersonalData()));
                    Department department = departmentRepo.findById(student.getDepartment().getId())
                                    .orElse(null);
                    dsc.setDepartment(UniversityFacultyDepartmentConverter.departmentDto(department));
                    List<Long> bookIds = student.getStudentBooks().stream().map(sb -> sb.getBook().getId())
                            .collect(Collectors.toList());
                    List<BookDto> bookDtos = bookRepo.findAllById(bookIds).stream()
                            .map(BookAuthorLibraryPublisherConverter::bookDto).toList();
                    dsc.getBooks().addAll(bookDtos);
                    List<CourseDto> courseDtos = courseRepo.findAllById(
                                    student.getStudentCourses().stream().map(StudentCourse::getCourse)
                                            .map(Course::getId).collect(Collectors.toList())
                            ).parallelStream()
                            .map(StudentTeacherCourseConverter::courseDto).toList();
                    dsc.getCourses().addAll(courseDtos);
                    List<Long> teacherIds = student.getStudentTeachers().stream()
                            .map(st -> st.getTeacher().getId()).collect(Collectors.toList());
                    List<TeacherDto> teacherDtos = teacherRepo.findByTeacherIds(teacherIds).stream()
                            .map(t -> StudentTeacherCourseConverter.teacherDto(t,departmentRepo,courseRepo))
                            .collect(Collectors.toList());
                    dsc.getTeachers().addAll(teacherDtos);
                    return dsc;
                }).findFirst().orElse(null);
    }

    public static Student student(StudentDto studentDto
            , UniversityRepo universityRepo, FacultyRepo facultyRepo, DepartmentRepo departmentRepo
            , CourseRepo courseRepo, TeacherCourseRepo teacherCourseRepo, TeacherRepo teacherRepo
            , BookRepo bookRepo, AuthorRepo authorRepo, LibraryRepo libraryRepo, PublisherRepo publisherRepo
            , BookAuthorRepo bookAuthorRepo, BookLibraryRepo bookLibraryRepo, BookPublisherRepo bookPublisherRepo
            ,StudentBookRepo studentBookRepo,StudentTeacherRepo studentTeacherRepo,StudentCourseRepo studentCourseRepo
            ,StudentRepo studentRepo){
        return Optional.ofNullable(studentDto).stream().map(src -> {
            Student dsc = new Student();
            BeanUtils.copyProperties(src,dsc);
            dsc.setAddress(AddressConverter.address(studentDto.getAddress()));
            // PersonalData
            Optional.ofNullable(src.getPersonalData()).ifPresent(personalDataDto -> {
                PersonalData personalData = PersonalDataConverter.personalData(personalDataDto);
                dsc.setPersonalData(personalData);
            });

            // Department
            Optional.ofNullable(src.getDepartment()).ifPresent(departmentDto -> {
                Department department = UniversityFacultyDepartmentConverter.department(departmentDto, universityRepo, facultyRepo);
                Optional.ofNullable(department).ifPresent(dept -> {
                    departmentRepo.save(dept);
                    dsc.addAssociation(department);
                });
            });
            // Book
            List<Book> books = src.getBooks().stream().map(bookDto
                    -> BookAuthorLibraryPublisherConverter.book(bookDto, bookRepo, authorRepo, libraryRepo, publisherRepo
                    , bookAuthorRepo, bookLibraryRepo, bookPublisherRepo)
            ).collect(Collectors.toList());

            // Course
            List<Course> courses = src.getCourses().stream().map(StudentTeacherCourseConverter::course)
                    .collect(Collectors.toList());
            courseRepo.saveAll(courses);
            // Teacher
            List<Teacher> teachers = src.getTeachers().stream().map(
                    teacherDto -> StudentTeacherCourseConverter.teacher(teacherDto
                    , universityRepo, facultyRepo, departmentRepo, courseRepo, teacherCourseRepo,teacherRepo)
            ).collect(Collectors.toList());

            studentRepo.save(dsc);
            // StudentBook
            studentBookRepo.saveAll(books.stream().map(book -> StudentBook.of(dsc,book)).collect(Collectors.toList()));

            // StudentCode
            studentCourseRepo.saveAll(courses.stream().map(course -> StudentCourse.of(dsc,course)).collect(Collectors.toList()));
            // StudentTeacher
            studentTeacherRepo.saveAll(teachers.stream().map(teacher -> StudentTeacher.of(dsc,teacher)).collect(Collectors.toList()));
            return dsc;
        }).findFirst().orElse(null);
    }
}
