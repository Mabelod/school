package ruhogwartsschool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ruhogwartsschool.model.Student;
import ruhogwartsschool.service.StudentService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("student")
public class StudentController {

    public StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.crateStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

//    @GetMapping()
//    public ResponseEntity<Collection<Student>> getAgeStudentInfo(@RequestParam Long age) {
//        List<Student> students = studentService.AgeStudent(age);
//        return ResponseEntity.ok(students);
//    }

    @GetMapping()
    public ResponseEntity findStudent(@RequestParam("min") int min, @RequestParam("max") int max) {
        return ResponseEntity.ok(studentService.findByAge(min, max));
    }

    @GetMapping("findFaculty")
    public ResponseEntity findStudentFaculty(@RequestParam("idStudent") int idStudent) {
        return ResponseEntity.ok(studentService.findByFaculty(idStudent));
    }

    @GetMapping("/totalCount")
    public int totalCountOfStudents() {
        return studentService.totalCountOfStudents();
    }

    @GetMapping("/averageAge")
    public double averageAgeOfStudents() {
        return studentService.averageAgeOfStudents();
    }

    @GetMapping("/lastStudents")
    public List<Student> lastStudents(@RequestParam @Min(1) @Max(10) int count) {
        return studentService.lastStudents(count);
    }

    @GetMapping("/findStudentNamesWhichStartedWithA")
    public Stream<String> findStudentNamesWhichStartedWithA() {
        return studentService.findStudentNamesWhichStartedWithA();
    }

    @GetMapping("/findStudentAverageAge")
    public double findStudentAverageAge() {
        return studentService.findStudentAverageAge();
    }
}

//    @GetMapping("faculty")
//    public ResponseEntity findStudentFaculty(@RequestParam("idFaculty") String name) {
//        return ResponseEntity.ok(studentService.findByFaculty(name));
//    }
