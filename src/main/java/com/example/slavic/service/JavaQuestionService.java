package com.example.slavic.service;

import com.example.slavic.dto.Question;
import com.example.slavic.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions;
    private final Random random = new Random();


    public JavaQuestionService(Set<Question> questions) {
        this.questions = new HashSet<>();
    }


    @Override
    public Question add(String question, String answer) {
        return add(new Question(question,answer));
    }


    @Override
    public Question add(Question question) {
         questions.add(question);
         return question;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {

        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new RuntimeException("список вопросов пуст");
        }

        // Преобразовать Set в List
        List<Question> questionList = List.copyOf(questions);

        // Получить случайный индекс из List
        int randomIndex = random.nextInt(questionList.size());

        // Вернуть элемент по случайному индексу
        return questionList.get(randomIndex);

        // return questions.toArray(Question[]::new)[random.nextInt(questions.size())];
    }
}
