package seng201.team0.models;

import java.util.function.Consumer;

public class GameEnvironment {
    private final Consumer<GameEnvironment> setupScreenLauncher;
    private final Runnable clearScreen;

    public GameEnvironment(Consumer<GameEnvironment> setupScreenLauncher, Runnable clearScreen) {
        this.clearScreen = clearScreen;
        this.setupScreenLauncher = setupScreenLauncher;
        launchSetupScreen();
    }

    public void launchSetupScreen() {
        setupScreenLauncher.accept(this);
    }
}
