package com.example.ITS.Service;

import com.example.ITS.Entity.CourseResource;
import com.example.ITS.Entity.Student;
import com.example.ITS.Entity.User;
import com.example.ITS.Repository.CourseResourceRepository;
import com.example.ITS.Repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseResourceRepository courseResourceRepository;
    @Autowired
    private HttpSession httpSession;

    public Student findStudentById(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateSelfInfo(Student student) {
        return studentRepository.save(student);
    }

    public List<CourseResource> findAllCourseResources() {
        return studentRepository.findAllCourseResources();
    }

    public List<CourseResource> findChosenCourseResource(long studentId) {
        return studentRepository.findChosenCourseResource(studentId);
    }    

    public double calculateResourcePercentage() {
        // 从 HttpSession 中获取用户
        User user = (User) httpSession.getAttribute("user");

        // 获取用户ID
        Long userId = user.getStudent().getId();

        List<CourseResource> studentResources = studentRepository.findChosenCourseResource(userId);
        List<CourseResource> totalResources = studentRepository.findAllCourseResources();

        if (totalResources.size() == 0) {
            return 0; // 避免除以0的情况
        }

        return ((double) studentResources.size() / totalResources.size()) * 100;
    }

    public boolean chooseCourseResource(long studentId,long resourceId ) {
        try {
            Student student = studentRepository.findById(studentId).orElse(null);
            CourseResource resource = courseResourceRepository.findById(resourceId).orElse(null);

            if (student != null && resource != null) {
                student.getCourseResources().add(resource);
                studentRepository.save(student);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // handle exception
            return false;
        }
    }


}
