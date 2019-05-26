package com.myexample.mybingo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainViewModel extends ViewModel {
    private final Random random = new Random();

    private final List<Integer> histories = new ArrayList<>();
    private final List<Integer> pickNumbers = new ArrayList<>();

    private final MutableLiveData<State> state = new MutableLiveData<>();

    public MainViewModel() {
        for (int i = 0; i <= 75; i++) {
            pickNumbers.add(i);
        }
    }

    LiveData<State> getState() {
        return state;
    }

    void pickNextNumber() {
        final int index = random.nextInt(pickNumbers.size());
        final int nextNumber = pickNumbers.remove(index);

        histories.add(nextNumber);
        state.setValue(new State(nextNumber, createHistoryText(), isAllPicked()));
    }

    private String createHistoryText() {
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (int history : histories) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(", ");
            }
            sb.append(history);
        }
        return sb.toString();
    }

    private boolean isAllPicked() {
        return pickNumbers.isEmpty();
    }

    static class State {
        final int nextNumber;
        final String historyText;
        final boolean isAllPicked;

        State(int nextNumber, String historyText, boolean isAllPicked) {
            this.nextNumber = nextNumber;
            this.historyText = historyText;
            this.isAllPicked = isAllPicked;
        }
    }
}
