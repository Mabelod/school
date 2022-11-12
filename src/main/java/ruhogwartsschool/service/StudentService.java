package ruhogwartsschool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ruhogwartsschool.model.Faculty;
import ruhogwartsschool.model.Student;
import ruhogwartsschool.repository.StudentRepository;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private static final Logger LOG = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student crateStudent(Student student) {
        LOG.debug("Method crateStudent was invoked");
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        LOG.debug("Method findStudent was invoked");
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        LOG.debug("Method editStudent was invoked");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        LOG.debug("Method deleteStudent was invoked");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        LOG.debug("Method getAllStudents was invoked");
        return studentRepository.findAll();
    }

    public List<Student> AgeStudent(Long age) {
        return getAllStudents().stream().filter(e -> e.getAge() == age).collect(Collectors.toList());
    }

    public Collection<Student> findByAge(int min, int max) {
        LOG.debug("Method findByAge was invoked");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty findByFaculty(long idStudent) {
        LOG.debug("Method findByFaculty was invoked");
        return studentRepository.findById(idStudent).get().getFaculty();
    }

    public int totalCountOfStudents() {
        LOG.debug("Method totalCountOfStudents was invoked");
        return studentRepository.totalCountOfStudents();
    }

    public double averageAgeOfStudents() {
        LOG.debug("Method averageAgeOfStudents was invoked");
        return studentRepository.averageAgeOfStudents();
    }

    public List<Student> lastStudents(int count) {
        LOG.debug("Method lastStudents was invoked");
        return studentRepository.lastStudents(count);
    }

    public Stream<String> findStudentNamesWhichStartedWithA() {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("А"))
                .sorted();
    }

    public double findStudentAverageAge() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .getAsDouble();
    }
}
