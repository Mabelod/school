package ruhogwartsschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ruhogwartsschool.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

//    Collection<Faculty> findFacultiesByColorContainingIgnoreCaseAndName(String color, String name);
    Collection<Faculty> findFacultiesByColorContainingIgnoreCase(String color);
    Collection<Faculty> findFacultiesByNameContainingIgnoreCase(String name);


}
