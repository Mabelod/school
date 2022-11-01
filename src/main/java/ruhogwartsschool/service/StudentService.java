package ruhogwartsschool.service;

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

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student crateStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> AgeStudent(Long age) {
        return getAllStudents().stream().filter(e -> e.getAge() == age).collect(Collectors.toList());
    }

    public Collection<Student> findByAge(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty findByFaculty(long idStudent) {
        return studentRepository.findById(idStudent).get().getFaculty();
    }

    public int totalCountOfStudents() {
        return studentRepository.totalCountOfStudents();
    }

    public double averageAgeOfStudents() {
        return studentRepository.averageAgeOfStudents();
    }

    public List<Student> lastStudents(int count) {
        return studentRepository.lastStudents(count);
    }
}
