package com.myexample.mybingo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView historyView;
    private TextView numberView;
    private View button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyView = findViewById(R.id.historyView);
        numberView = findViewById(R.id.numberView);
        button = findViewById(R.id.button);

        final MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        ((View) button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.pickNextNumber();
            }
        });

        viewModel.getState().observe(this, new Observer<MainViewModel.State>() {
            @Override
            public void onChanged(MainViewModel.State state) {
                numberView.setText(""+state.nextNumber);

                historyView.setText(state.historyText);

                if (state.isAllPicked) {
                    button.setEnabled(false);
                }
            }
        });
    }
}
