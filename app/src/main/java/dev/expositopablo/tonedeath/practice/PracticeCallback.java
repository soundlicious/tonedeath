package dev.expositopablo.tonedeath.practice;

interface PracticeCallback {
    void gameOver();
    void extraLife();
    boolean isAdLoaded();
    void updateWidget();
    PracticeViewModel getViewModel();
    void backToGame();
}
