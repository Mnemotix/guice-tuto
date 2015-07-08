package com.mnemotix.tuto.services;

import com.mnemotix.tuto.models.Student;

public interface StudentStore {

    public boolean exists(Integer id);

    public Student load(Integer id);

    public Student save(Student p);
}