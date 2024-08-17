package com.example.demo.StudentAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.StudentAPI.StudentModel.Gender;

@RestController
public class StudentAPI {

    private final List<StudentModel> students = new ArrayList<>();

    @GetMapping("api/students/get")
    public List<StudentModel> getStudents(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "gender", required = false) Gender gender,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "studentId", required = false) String studentId
    ) {
        return students.stream()
                .filter(student -> (studentId == null || student.getId().equals(studentId))
                && (name == null || student.getName().equals(name))
                && (age == null || student.getAge() == age)
                && (gender == null || student.getGender() == gender)
                && (address == null || student.getAddress().equals(address)))
                .collect(Collectors.toList());
    }

    @PostMapping("api/students/add")
    public List<StudentModel> addStudents(StudentModel student) {
        for (StudentModel studentModel : students) {
            this.students.add(studentModel);
        }
        return this.students;
    }

    @PatchMapping("api/students/update")
    public List<StudentModel> updateStudents(@RequestParam("studentId") String studentId, StudentModel student, @RequestBody StudentModel updatedStudent) {
        students.stream()
                .filter(studentModel -> studentModel.getId().equals(studentId))
                .findFirst()
                .ifPresent(studentModel -> {
                    studentModel.setName(updatedStudent.getName());
                    studentModel.setAge(updatedStudent.getAge());
                    studentModel.setGender(updatedStudent.getGender());
                    studentModel.setAddress(updatedStudent.getAddress());
                });
        return this.students;
    }

    @DeleteMapping("api/students/delete")
    public List<StudentModel> deleteStudents(@RequestParam("studentId") String studentId) {
        students.removeIf(studentModel -> studentModel.getId().equals(studentId));
        return this.students;
    }
}
