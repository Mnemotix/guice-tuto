package com.mnemotix.tuto.services;

import com.mnemotix.tuto.models.Student;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nico on 08/07/2015.
 */
@Singleton
public class DummyStudentStore implements StudentStore {

    private final Map<Integer, Student> studentMap;

    public DummyStudentStore() {
        studentMap = new HashMap<Integer, Student>();
    }

    public boolean exists(Integer id) {
        return studentMap.containsKey(id);
    }

    public Student load(Integer id) {
        Student s = studentMap.get(id);
        return s;
    }

    public Student save(Student p) {
        if (p.getId() == null) {
            p.setId(studentMap.size() + 1);
        }
        studentMap.put(p.getId(), p);
        return p;
    }
}
