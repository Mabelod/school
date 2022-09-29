package ruhogwartsschool.service;

import org.springframework.stereotype.Service;
import ruhogwartsschool.model.Faculty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    public Faculty crateFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> getAllFaculty() {
        return new ArrayList<>(faculties.values());
    }

    public List<Faculty> colorFaculty(String color) {
        return getAllFaculty().stream().filter(e -> e.getColor().equalsIgnoreCase(color)).collect(Collectors.toList());
    }
}
