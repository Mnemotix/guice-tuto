package com.mnemotix.tuto.services;

import com.mnemotix.tuto.models.Student;

public interface Registrar {

    public boolean checkStudentStatus(Integer studentId);

    public Student registerStudent(String name, Integer credits);
}