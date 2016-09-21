package automation.qa.espressoexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnTextInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTextInputLayout = (Button) findViewById(R.id.btnTextInputLayout);

        btnTextInputLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTextInputLayout:
                Intent intent = new Intent(this, TextInputLayoutActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
