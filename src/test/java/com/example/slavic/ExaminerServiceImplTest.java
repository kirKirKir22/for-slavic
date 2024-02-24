package com.example.slavic;


import com.example.slavic.dto.Question;
import com.example.slavic.exception.TooManyQuestionsException;
import com.example.slavic.service.ExaminerServiceImpl;
import com.example.slavic.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    QuestionService questionService;

    @InjectMocks
    ExaminerServiceImpl underTest;

    private static Collection<Question> questionsTest() {
        return List.of(
                new Question("вопрос1", "ответ1"),
                new Question("вопрос2", "ответ2"),
                new Question("вопрос3", "ответ3"),
                new Question("вопрос4", "ответ5"),
                new Question("вопрос5", "ответ5")
        );
    }

    @Test
    void qetQuestions_amountMoreSize_thrownTooManyRequestException() {
        when(questionService.getAll()).thenReturn(questionsTest());
        assertThrows(TooManyQuestionsException.class, () -> underTest.getQuestions(6));
    }

    @Test
    void getQuestions_validAmount_returnsQuestions() {

        // Подготовка данных
        when(questionService.getAll()).thenReturn(questionsTest());
        when(questionService.getRandomQuestion())
                .thenReturn(new Question("вопрос1", "ответ1"))
                .thenReturn(new Question("вопрос2", "ответ2"))
                .thenReturn(new Question("вопрос3", "ответ3"))
                .thenReturn(new Question("вопрос4", "ответ4"))
                .thenReturn(new Question("вопрос5", "ответ5"));

        // Вызов тестируемого метода
        Collection<Question> result = underTest.getQuestions(3);

        // Проверка результатов
        assertEquals(3, result.size());

        // Убедимся в уникальности вопросов в коллекции
        assertEquals(result.size(), new HashSet<>(result).size());

        // Можете добавить более конкретные проверки в зависимости от ваших требований
        // Например, можно проверить, что возвращенные вопросы соответствуют ожидаемым.
        // Также можно проверить, что вопросы не являются null, и т.д.

        // Проверим, что вопросы возвращаются из предоставленного списка
        assertTrue(questionsTest().containsAll(result));
    }
}