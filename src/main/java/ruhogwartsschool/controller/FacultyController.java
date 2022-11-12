package ruhogwartsschool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ruhogwartsschool.model.Faculty;
import ruhogwartsschool.model.Student;
import ruhogwartsschool.service.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    public FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.crateFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    //    @GetMapping()
//    public ResponseEntity<Collection<Faculty>> getColorFacultyInfo(@RequestParam String color) {
//        List<Faculty> colors = facultyService.colorFaculty(color);
//        return ResponseEntity.ok(colors);
//    }
    @GetMapping()
    public ResponseEntity getColorFacultyInfo(@RequestParam(required = false) String color,
                                              @RequestParam(required = false) String name) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findFacultyColor(color));
        }
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findFacultyName(name));
        }
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("findStudent")
    public ResponseEntity<Collection<Student>> findFacultyStudents(@RequestParam("idFaculty") int idFaculty) {
        return ResponseEntity.ok(facultyService.findByStudent(idFaculty));
    }

    @GetMapping("/findTheLongestFacultyName")
    public String findTheLongestFacultyName() {
        return facultyService.findTheLongestFacultyName();
    }
}
