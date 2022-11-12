package ruhogwartsschool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ruhogwartsschool.model.Faculty;
import ruhogwartsschool.model.Student;
import ruhogwartsschool.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private static final Logger LOG = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty crateFaculty(Faculty faculty) {
        LOG.debug("Method crateFaculty was invoked");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        LOG.debug("Method findFaculty was invoked");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        LOG.debug("Method editFaculty was invoked");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        LOG.debug("Method deleteFaculty was invoked");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculty() {
        LOG.debug("Method getAllFaculty was invoked");
        return facultyRepository.findAll();
    }

    public List<Faculty> colorFaculty(String color) {
        return getAllFaculty().stream().filter(e -> e.getColor().equalsIgnoreCase(color)).collect(Collectors.toList());
    }
    public Collection<Faculty> findFacultyColor(String color) {
        LOG.debug("Method findFacultyColor was invoked");
        return facultyRepository.findFacultiesByColorContainingIgnoreCase(color);
    }
    public Collection<Faculty> findFacultyName(String name) {
        LOG.debug("Method findFacultyName was invoked");
        return facultyRepository.findFacultiesByNameContainingIgnoreCase(name);
    }

    public Collection<Student> findByStudent(long idFaculty) {
        LOG.debug("Method findByStudent was invoked");
        return facultyRepository.findById(idFaculty).get().getStudents();
    }

    public String findTheLongestFacultyName() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .get();
    }
}
