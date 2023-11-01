package racingcar.domain;

import racingcar.view.Output;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Car> cars = new ArrayList<>();
    private int numberOfAttempts;

    public Game(String carNames, int numberOfAttempts) {
        initializeCars(carNames);
        this.numberOfAttempts = numberOfAttempts;
    }

    private void initializeCars(String carNames) {
        String[] names = carNames.split(",");
        for (String name : names) {
            name = name.trim();
            cars.add(new Car(name));
        }
    }

    public void startGame() {
        currentCondition();
    }

    public void currentCondition() {
        Output.attemptResultMessage();
        for (int attempt = 0; attempt < numberOfAttempts; attempt++) {
            for (Car car : cars) {
                car.move();
                car.currentLocation();
            }
            System.out.println();
        }
    }

    public void printWinners() {
        List<String> winners = new ArrayList<>();

        setWinners(winners);
        getWinners(winners);
    }

    private void setWinners(List<String> winners) {
        int maxForwardCount = 0;

        for (Car car : cars) {
            if (car.getForwardCount() > maxForwardCount) {
                maxForwardCount = car.getForwardCount();
                winners.clear();
                winners.add(car.getName());
            } else if (car.getForwardCount() == maxForwardCount) {
                winners.add(car.getName());
            }
        }
    }

    private void getWinners(List<String> winners) {
        Output.winnerMessage();
        for (int i = 0; i < winners.size(); i++) {
            System.out.print(winners.get(i));
            if (i < winners.size() - 1) {
                System.out.print(", ");
            }
        }
    }
}
