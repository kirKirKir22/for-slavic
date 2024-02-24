package com.example.slavic;

import com.example.slavic.dto.Question;
import com.example.slavic.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class JavaQuestionServiceTest {

    private JavaQuestionService underTest;

    @BeforeEach
    void setUp() {
        // Инициализация сервиса перед каждым тестом
        Set<Question> questions = new HashSet<>();
        underTest = new JavaQuestionService(questions);
    }

    @Test
    void add_WithQuestionAndAnswer_ReturnsAddedQuestion() {
        // Добавление вопроса с вопросом и ответом, проверка возвращаемого значения
        Question addQuestion = underTest.add("question","answer");
        assertNotNull(addQuestion);
        assertEquals("question",addQuestion.getQuestion());
        assertEquals("answer",addQuestion.getAnswer());
    }

    @Test
    void add_WithQuestionObject_ReturnsAddedQuestion() {
        // Добавление вопроса с использованием объекта вопроса, проверка возвращаемого значения
        Question question = new Question("question","answer");
        Question addedQuestion = underTest.add(question);
        assertNotNull(addedQuestion);
        assertTrue(underTest.getAll().contains(question));
    }

    @Test
    void remove_Question_ReturnsRemovedQuestion() {
        // Удаление вопроса, проверка возвращаемого значения и отсутствия вопроса после удаления
        Question questionToRemove = new Question("question", "answer");
        Question removedQuestion = underTest.remove(questionToRemove);
        assertNotNull(removedQuestion);
        assertFalse(underTest.getAll().contains(questionToRemove));
    }

    @Test
    void getAll_EmptyService_ReturnsEmptySet() {
        // Получение всех вопросов из пустого сервиса, проверка наличия коллекции и ее пустоты
        Collection<Question> allQuestions = underTest.getAll();
        assertNotNull(allQuestions);
        assertTrue(allQuestions.isEmpty());
    }

    @Test
    void getAll_NonEmptyService_ReturnsAllQuestions() {
        // Создаем и добавляем несколько вопросов в сервис
        Question question1 = new Question("question 1", "answer 1");
        Question question2 = new Question("question 2", "answer 2");
        underTest.add(question1);
        underTest.add(question2);

        // Получаем все вопросы из сервиса
        Collection<Question> allQuestions = underTest.getAll();

        // Проверяем, что коллекция не является пустой
        assertNotNull(allQuestions);

        // Проверяем, что коллекция содержит добавленные вопросы
        assertTrue(allQuestions.contains(question1));
        assertTrue(allQuestions.contains(question2));

        // Проверяем размер коллекции (опционально)
        assertEquals(2, allQuestions.size());
    }

    @Test
    void getRandomQuestion_NonEmptyService_ReturnsRandomQuestion() {
        // Добавление вопросов, вызов метода получения случайного вопроса, проверка возвращенного вопроса и его наличия в списке
        Question question1 = new Question("question 1", "answer 1");
        Question question2 = new Question("question 2", "answer 2");
        underTest.add(question1);
        underTest.add(question2);

        Question randomQuestion = underTest.getRandomQuestion();

        assertNotNull(randomQuestion);
        assertTrue(underTest.getAll().contains(randomQuestion));
    }
}