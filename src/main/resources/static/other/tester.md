teacherCourseRepo.saveAll(courses.stream().map(course -> {
Long id = course.getId();
return teachers.stream().filter(teacher -> (teacher.getId() % (BOOK_SIZE / 2)) == id)
.map(teacher -> StudentBook.of(teacher,course)).collect(Collectors.toList());
}).flatMap(Collection::stream).collect(Collectors.toList())
);