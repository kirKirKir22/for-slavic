package com.example.slavic.service;

import com.example.slavic.dto.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
