package com.driver.services;

import com.driver.models.Card;
import com.driver.models.Student;
import com.driver.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    @Autowired
    CardService cardService4;

    @Autowired
    StudentRepository studentRepository4;

    public Student getDetailsByEmail(String email){
        Student student = null;
        student = studentRepository4.findByEmailId(email);
        return student;
    }

    public Student getDetailsById(int id){
        Student student = null;
        student = studentRepository4.findById(id).get();
        return student;
    }

    public void createStudent(Student student){
        studentRepository4.save(student); // yeh na bhi likhe tab bhi save ho jaega
        cardService4.createAndReturn(student);
    }

    public void updateStudent(Student student){
        studentRepository4.updateStudentDetails(student);
    }

    public void deleteStudent(int id){
       // studentRepository4.deleteById(id);
        cardService4.deactivateCard(id); // pehle deactivate phir delete kyuki deactivateCard ki query student table pe chal rhi hain
        studentRepository4.deleteCustom(id);
        //Delete student and deactivate corresponding card
    }
}
