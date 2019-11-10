package dev.expositopablo.tonedeath.practice;

interface PracticeCallback {
    void gameOver();
    void updateWidget();
    PracticeViewModel getViewModel();
    void backToGame();
}
