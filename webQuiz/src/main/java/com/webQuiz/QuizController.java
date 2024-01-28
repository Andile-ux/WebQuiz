package com.webQuiz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuizController {

    private List<Question> data;
    private int now;
    private int score;
    private String username;

    public QuizController() {
        initializeData();
        now = 0;
        score = 0;
        username = "";
    }

    @GetMapping("/quiz")
    public String getQuiz(Model model) {
        if (username.isEmpty()) {
            return "quiz/userInput";
        }

        if (now < data.size()) {
            model.addAttribute("question", data.get(now));
            return "quiz/quiz";
        } else {
            model.addAttribute("username", username);
            model.addAttribute("score", score);
            return "quiz/result";
        }
    }

    // Other methods for handling user input, scoring, and result display go here

    private void initializeData() {
        data = new ArrayList<>();
        data.add(new Question("What is the largest organ in the human body?",
                new String[]{"Lungs", "Heart", "Kidneys", "Liver"}, 1));
        data.add(new Question("What is the percentage of the earth covered by water?",
                new String[]{"51%", "61%", "71%", "81%"}, 3));
        data.add(new Question("What is the atomic number of hydrogen?",
                new String[]{"2", "4", "1", "3"}, 2));
        data.add(new Question("What is the oldest university in the UK?",
                new String[]{"Oxford", "Cambridge", "Manchester", "Bath"}, 0));
        data.add(new Question("In the Big Bang Theory, what is the name of Sheldon and Leonard's neighbor?",
                new String[]{"Lily", "Jessie", "Patty", "Penny"}, 3));
    }

    // Nested Question class (or use a separate file)
    private static class Question {
        private String question;
        private String[] options;
        private int answer;

        public Question(String question, String[] options, int answer) {
            this.question = question;
            this.options = options;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public String[] getOptions() {
            return options;
        }

        public int getAnswer() {
            return answer;
        }
    }
}

