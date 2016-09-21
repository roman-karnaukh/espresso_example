package automation.qa.espressoexample;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class TextInputLayoutActivity extends AppCompatActivity implements View.OnFocusChangeListener {


    private TextInputLayout textInputLayout;
    private EditText etEmail, etName, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etName = (EditText) findViewById(R.id.etName);
        etPass = (EditText) findViewById(R.id.etPass);

        textInputLayout = (TextInputLayout) findViewById(R.id.inputLayout);
        textInputLayout.setErrorEnabled(true);

        etEmail.setOnFocusChangeListener(this);
        etName.setOnFocusChangeListener(this);
        etPass.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        EditText editText = (EditText) v;
        if (!hasFocus) {
            validateEditText(editText.getText());
        }
    }

    private void validateEditText(Editable s) {
        if (!TextUtils.isEmpty(s)) {
            textInputLayout.setError(null);
        }
        else{
            textInputLayout.setError(getString(R.string.error));
        }
    }

}
