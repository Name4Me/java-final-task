package com.example.app.model;

public class TestResult {
    private final String email;
    private final String testName;
    private final int score;

    public TestResult(String email, String testName, int score) {
        this.email = email;
        this.testName = testName;
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public String getTestName() {
        return testName;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "email='" + email + '\'' +
                ", testName='" + testName + '\'' +
                ", score=" + score +
                '}';
    }
}
