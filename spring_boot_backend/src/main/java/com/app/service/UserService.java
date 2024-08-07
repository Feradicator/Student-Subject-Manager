package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dto.StudentSubjectsDTO;
import com.app.model.Subject;
import com.app.model.User;
import com.app.repository.SubjectRepository;
import com.app.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public User createStudent(User student) {
        return studentRepository.save(student);
    }

    public List<User> getAllStudents() {
        return studentRepository.findAll();
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
        User user=studentRepository.findByEmail(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("User not found with email"+username);

        }
        List<GrantedAuthority>authorities=new ArrayList<>();

		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}

    public User enrollStudentInSubject(Long studentId, Long subjectId) {
        Optional<User> studentOptional = studentRepository.findById(studentId);
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
        System.out.println("find id");

        if (studentOptional.isPresent() && subjectOptional.isPresent()) {
            User student = studentOptional.get();
            Subject subject = subjectOptional.get();
            student.getSubjects().add(subject);
            return studentRepository.save(student);
        }

        throw new RuntimeException("Student or Subject not found");
    }

    public StudentSubjectsDTO getStudentSubjects(Long studentId) {
        Optional<User> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            User student = studentOptional.get();
            List<String> subjectNames = student.getSubjects().stream()
                                               .map(subject -> subject.getName())
                                               .collect(Collectors.toList());
            return new StudentSubjectsDTO(student.getName(), subjectNames.size(), subjectNames);
        }

        throw new RuntimeException("Student not found");
    }
}
