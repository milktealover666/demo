package com.example.ITS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Course {

    // id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // 标题
    private String title;

    // 描述
    private String description;

    // 分类
    @ManyToOne
    private CourseCategory category;

    // 课程资源
    @OneToMany(mappedBy = "course")
    private List<CourseResource> resources;
}
