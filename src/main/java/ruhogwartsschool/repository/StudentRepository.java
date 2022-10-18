package ruhogwartsschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruhogwartsschool.model.Faculty;
import ruhogwartsschool.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);
}
