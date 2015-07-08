package com.mnemotix.tuto.services;

import com.mnemotix.tuto.models.Student;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LenientRegistrar implements Registrar {

    private final StudentStore studentStore;

    @Inject
    public LenientRegistrar(StudentStore studentStore) {
        this.studentStore = studentStore;
    }

    public boolean checkStudentStatus(Integer studentId) {
        boolean status = false;

        Student student = studentStore.load(studentId);

        if (student != null && student.getCredits() != null) {
            status = student.getCredits() >= 10.0;
        }
        return status;

    }

    public Student registerStudent(String name, Integer credits) {
        Student s = new Student();
        s.setName(name);
        s.setCredits(credits);
        return studentStore.save(s);
    }

}
