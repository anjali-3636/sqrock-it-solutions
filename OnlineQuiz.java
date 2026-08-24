import java.util.Scanner;

public class OnlineQuiz {

    static Scanner sc = new Scanner(System.in);

    // Questions
    static String[] questions = {
        "1. Which language is used for Java programming?",
        "2. Which keyword is used to create a class in Java?",
        "3. Which method is the starting point of a Java program?",
        "4. Which data type is used to store whole numbers?",
        "5. Which symbol is used to end a statement in Java?"
    };

    // Options
    static String[][] options = {
        {"A. Python", "B. Java", "C. HTML", "D. SQL"},
        {"A. class", "B. Class", "C. create", "D. newclass"},
        {"A. start()", "B. run()", "C. main()", "D. begin()"},
        {"A. double", "B. String", "C. boolean", "D. int"},
        {"A. .", "B. :", "C. ;", "D. ,"}
    };

    // Correct answers
    static char[] answers = {'B', 'A', 'C', 'D', 'C'};

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("       ONLINE QUIZ APPLICATION");
        System.out.println("======================================");

        // Registration
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.println("\nWelcome, " + name + "!");
        System.out.println("Please answer all the questions.");
        System.out.println("Enter A, B, C or D.");
        System.out.println("--------------------------------------");

        int score = 0;

        // Display questions
        for (int i = 0; i < questions.length; i++) {

            System.out.println("\n" + questions[i]);

            for (String option : options[i]) {
                System.out.println(option);
            }

            char userAnswer;

            while (true) {
                System.out.print("Your answer: ");
                String input = sc.nextLine().toUpperCase();

                if (input.length() == 1 &&
                    (input.charAt(0) == 'A' ||
                     input.charAt(0) == 'B' ||
                     input.charAt(0) == 'C' ||
                     input.charAt(0) == 'D')) {

                    userAnswer = input.charAt(0);
                    break;

                } else {
                    System.out.println("Invalid answer! Please enter A, B, C or D.");
                }
            }

            if (userAnswer == answers[i]) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong!");
                System.out.println("Correct answer: " + answers[i]);
            }
        }

        // Result
        int totalQuestions = questions.length;
        double percentage = ((double) score / totalQuestions) * 100;

        System.out.println("\n======================================");
        System.out.println("              QUIZ RESULT");
        System.out.println("======================================");

        System.out.println("Student Name : " + name);
        System.out.println("Total Questions : " + totalQuestions);
        System.out.println("Correct Answers : " + score);
        System.out.println("Wrong Answers : " + (totalQuestions - score));
        System.out.println("Percentage : " + percentage + "%");

        if (percentage >= 40) {
            System.out.println("Result : PASS");
        } else {
            System.out.println("Result : FAIL");
        }

        System.out.println("======================================");
        System.out.println("       Thank you for taking the quiz!");
        System.out.println("======================================");

        sc.close();
    }
}