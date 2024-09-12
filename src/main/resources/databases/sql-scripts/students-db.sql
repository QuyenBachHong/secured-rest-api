CREATE TABLE ADDRESSES
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    street_name VARCHAR(255)          NOT NULL,
    time_zone   VARCHAR(255)          NOT NULL,
    latitude    VARCHAR(255)          NOT NULL,
    longitude   VARCHAR(255)          NOT NULL,
    cityName    VARCHAR(255)          NOT NULL,
    zipCode     VARCHAR(255)          NOT NULL,
    state       VARCHAR(255)          NOT NULL,
    countryCode VARCHAR(255)          NOT NULL,
    countryName VARCHAR(255)          NOT NULL,
    capital     VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_addresses PRIMARY KEY (id)
);
CREATE TABLE AUTHORS
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    authorCode    VARCHAR(10)           NOT NULL,
    pseudonym     VARCHAR(255)          NOT NULL,
    authorEmail   VARCHAR(255)          NOT NULL,
    authorUrl     VARCHAR(255)          NOT NULL,
    streetAddress VARCHAR(255),
    cityName      VARCHAR(255),
    state         VARCHAR(255),
    country       VARCHAR(255),
    fullName      VARCHAR(100),
    citizenCode   VARCHAR(20),
    firstName     VARCHAR(255),
    lastName      VARCHAR(255),
    birthday      date,
    gender        VARCHAR(255),
    phoneNumber   VARCHAR(255),
    creditLimit   DECIMAL,
    CONSTRAINT pk_authors PRIMARY KEY (id)
);

CREATE TABLE BOOKS
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    bookIsbn  VARCHAR(20),
    bookTitle VARCHAR(255)          NOT NULL,
    bookGenre VARCHAR(255)          NOT NULL,
    bookPrice DECIMAL               NOT NULL,
    CONSTRAINT pk_books PRIMARY KEY (id)
);

CREATE TABLE LIBRARIES
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    libraryCode   VARCHAR(8)            NOT NULL,
    libraryName   VARCHAR(255)          NOT NULL,
    libraryUrl    VARCHAR(255)          NOT NULL,
    libraryEmail  VARCHAR(255)          NOT NULL,
    streetAddress VARCHAR(255),
    cityName      VARCHAR(255),
    state         VARCHAR(255),
    country       VARCHAR(255),
    latitude      VARCHAR(255),
    longitude     VARCHAR(255),
    CONSTRAINT pk_libraries PRIMARY KEY (id)
);

CREATE TABLE PUBLISHERS
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    publisherCode  VARCHAR(8)            NOT NULL,
    publisherName  VARCHAR(255)          NOT NULL,
    publisherUrl   VARCHAR(255)          NOT NULL,
    publisherEmail VARCHAR(255)          NOT NULL,
    streetAddress  VARCHAR(255),
    cityName       VARCHAR(255),
    state          VARCHAR(255),
    country        VARCHAR(255),
    latitude       VARCHAR(255),
    longitude      VARCHAR(255),
    CONSTRAINT pk_publishers PRIMARY KEY (id)
);

CREATE TABLE BOOKS_AUTHORS
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    bookId   BIGINT,
    authorId BIGINT,
    CONSTRAINT pk_books_authors PRIMARY KEY (id)
);

ALTER TABLE BOOKS_AUTHORS
    ADD CONSTRAINT FK_BOOKS_AUTHORS_ON_AUTHORID FOREIGN KEY (authorId) REFERENCES AUTHORS (id);

ALTER TABLE BOOKS_AUTHORS
    ADD CONSTRAINT FK_BOOKS_AUTHORS_ON_BOOKID FOREIGN KEY (bookId) REFERENCES BOOKS (id);

CREATE TABLE BOOKS_LIBRARIES
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    bookId    BIGINT,
    libraryId BIGINT,
    CONSTRAINT pk_books_libraries PRIMARY KEY (id)
);

ALTER TABLE BOOKS_LIBRARIES
    ADD CONSTRAINT FK_BOOKS_LIBRARIES_ON_BOOKID FOREIGN KEY (bookId) REFERENCES BOOKS (id);

ALTER TABLE BOOKS_LIBRARIES
    ADD CONSTRAINT FK_BOOKS_LIBRARIES_ON_LIBRARYID FOREIGN KEY (libraryId) REFERENCES LIBRARIES (id);

CREATE TABLE BOOKS_PUBLISHERS
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    bookId      BIGINT,
    publisherId BIGINT,
    CONSTRAINT pk_books_publishers PRIMARY KEY (id)
);

ALTER TABLE BOOKS_PUBLISHERS
    ADD CONSTRAINT FK_BOOKS_PUBLISHERS_ON_BOOKID FOREIGN KEY (bookId) REFERENCES BOOKS (id);

ALTER TABLE BOOKS_PUBLISHERS
    ADD CONSTRAINT FK_BOOKS_PUBLISHERS_ON_PUBLISHERID FOREIGN KEY (publisherId) REFERENCES PUBLISHERS (id);


CREATE TABLE UNIVERSITIES
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    universityName   VARCHAR(200)          NOT NULL,
    universityCode   VARCHAR(20)           NOT NULL,
    universityPrefix VARCHAR(20)           NOT NULL,
    universitySuffix VARCHAR(20)           NOT NULL,
    emailAddress     VARCHAR(200)          NOT NULL,
    streetAddress    VARCHAR(255),
    cityName         VARCHAR(255),
    state            VARCHAR(255),
    country          VARCHAR(255),
    latitude         VARCHAR(255),
    longitude        VARCHAR(255),
    CONSTRAINT pk_universities PRIMARY KEY (id)
);

CREATE TABLE FACULTIES
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    facultyName     VARCHAR(255)          NOT NULL,
    facultyCode     VARCHAR(255)          NOT NULL,
    establishedDate date                  NOT NULL,
    streetAddress   VARCHAR(255),
    cityName        VARCHAR(255),
    state           VARCHAR(255),
    country         VARCHAR(255),
    latitude        VARCHAR(255),
    longitude       VARCHAR(255),
    universityId    BIGINT,
    CONSTRAINT pk_faculties PRIMARY KEY (id)
);

ALTER TABLE FACULTIES
    ADD CONSTRAINT FK_FACULTIES_ON_UNIVERSITYID FOREIGN KEY (universityId) REFERENCES UNIVERSITIES (id);

CREATE TABLE DEPARTMENTS
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    departmentName  VARCHAR(255)          NOT NULL,
    departmentCode  VARCHAR(255)          NOT NULL,
    establishedDate date                  NOT NULL,
    streetAddress   VARCHAR(255),
    cityName        VARCHAR(255),
    state           VARCHAR(255),
    country         VARCHAR(255),
    latitude        VARCHAR(255),
    longitude       VARCHAR(255),
    facultyId       BIGINT,
    CONSTRAINT pk_departments PRIMARY KEY (id)
);

ALTER TABLE DEPARTMENTS
    ADD CONSTRAINT FK_DEPARTMENTS_ON_FACULTYID FOREIGN KEY (facultyId) REFERENCES FACULTIES (id);


CREATE TABLE COURSES
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    courseName    VARCHAR(255)          NOT NULL,
    courseCode    VARCHAR(255)          NOT NULL,
    publishedDate date                  NOT NULL,
    CONSTRAINT pk_courses PRIMARY KEY (id)
);

CREATE TABLE TEACHERS
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    email           VARCHAR(255),
    status          BOOLEAN               NOT NULL,
    experiencedYear INT                   NOT NULL,
    academicDegree  VARCHAR(255),
    scienceDegree   VARCHAR(255),
    sciencePosition VARCHAR(255),
    address_id      BIGINT,
    departmentId    BIGINT,
    fullName        VARCHAR(100),
    citizenCode     VARCHAR(20),
    firstName       VARCHAR(255),
    lastName        VARCHAR(255),
    birthday        date,
    gender          VARCHAR(255),
    phoneNumber     VARCHAR(255),
    creditLimit     DECIMAL,
    CONSTRAINT pk_teachers PRIMARY KEY (id)
);

ALTER TABLE TEACHERS
    ADD CONSTRAINT FK_TEACHERS_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES ADDRESSES (id);

ALTER TABLE TEACHERS
    ADD CONSTRAINT FK_TEACHERS_ON_DEPARTMENTID FOREIGN KEY (departmentId) REFERENCES DEPARTMENTS (id);

CREATE TABLE STUDENTS
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    email        VARCHAR(255),
    status       BOOLEAN               NOT NULL,
    isSenior     BOOLEAN               NOT NULL,
    gradeLevel   VARCHAR(255),
    studentCode  VARCHAR(255),
    address_id   BIGINT,
    departmentId BIGINT,
    fullName     VARCHAR(100),
    citizenCode  VARCHAR(20),
    firstName    VARCHAR(255),
    lastName     VARCHAR(255),
    birthday     date,
    gender       VARCHAR(255),
    phoneNumber  VARCHAR(255),
    creditLimit  DECIMAL,
    CONSTRAINT pk_students PRIMARY KEY (id)
);

ALTER TABLE STUDENTS
    ADD CONSTRAINT FK_STUDENTS_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES ADDRESSES (id);

ALTER TABLE STUDENTS
    ADD CONSTRAINT FK_STUDENTS_ON_DEPARTMENTID FOREIGN KEY (departmentId) REFERENCES DEPARTMENTS (id);

CREATE TABLE STUDENTS_BOOKS
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    studentId BIGINT,
    bookId    BIGINT,
    CONSTRAINT pk_students_books PRIMARY KEY (id)
);

ALTER TABLE STUDENTS_BOOKS
    ADD CONSTRAINT FK_STUDENTS_BOOKS_ON_BOOKID FOREIGN KEY (bookId) REFERENCES BOOKS (id);

ALTER TABLE STUDENTS_BOOKS
    ADD CONSTRAINT FK_STUDENTS_BOOKS_ON_STUDENTID FOREIGN KEY (studentId) REFERENCES STUDENTS (id);

CREATE TABLE STUDENTS_COURSES
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    studentId BIGINT,
    courseId  BIGINT,
    CONSTRAINT pk_students_courses PRIMARY KEY (id)
);

ALTER TABLE STUDENTS_COURSES
    ADD CONSTRAINT FK_STUDENTS_COURSES_ON_COURSEID FOREIGN KEY (courseId) REFERENCES COURSES (id);

ALTER TABLE STUDENTS_COURSES
    ADD CONSTRAINT FK_STUDENTS_COURSES_ON_STUDENTID FOREIGN KEY (studentId) REFERENCES STUDENTS (id);

CREATE TABLE STUDENTS_TEACHERS
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    studentId BIGINT,
    teacherId BIGINT,
    CONSTRAINT pk_students_teachers PRIMARY KEY (id)
);

ALTER TABLE STUDENTS_TEACHERS
    ADD CONSTRAINT FK_STUDENTS_TEACHERS_ON_STUDENTID FOREIGN KEY (studentId) REFERENCES STUDENTS (id);

ALTER TABLE STUDENTS_TEACHERS
    ADD CONSTRAINT FK_STUDENTS_TEACHERS_ON_TEACHERID FOREIGN KEY (teacherId) REFERENCES TEACHERS (id);

CREATE TABLE TEACHERS_COURSES
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    teacherId BIGINT,
    courseId  BIGINT,
    CONSTRAINT pk_teachers_courses PRIMARY KEY (id)
);

ALTER TABLE TEACHERS_COURSES
    ADD CONSTRAINT FK_TEACHERS_COURSES_ON_COURSEID FOREIGN KEY (courseId) REFERENCES COURSES (id);

ALTER TABLE TEACHERS_COURSES
    ADD CONSTRAINT FK_TEACHERS_COURSES_ON_TEACHERID FOREIGN KEY (teacherId) REFERENCES TEACHERS (id);

