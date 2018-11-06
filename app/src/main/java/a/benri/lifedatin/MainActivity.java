package a.benri.lifedatin;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int i = 0;
    private Switch switchy, switchyAprobar;
    private boolean activado;
    private TextView txtViewInfo,txtViewInfoAprobar, txtTengoanos;
    private EditText etEdad;
    private Button BtnChange;
    private MutableLiveData<String> mCurrentName, age;
    private MutableLiveData<Boolean> mTrabaja, mEstudia;

    public MutableLiveData<String> getCurrentEdad(){
        if (age == null) {
            age = new MutableLiveData<String>();
        }
        return age;
    }
    public MutableLiveData<Boolean> getCurrentTrabajo(){
        if (mTrabaja == null) {
            mTrabaja = new MutableLiveData<Boolean>();
        }
        return mTrabaja;
    }
    public MutableLiveData<Boolean> getCurrentEstudio(){
        if (mEstudia == null) {
            mEstudia = new MutableLiveData<Boolean>();
        }
        return mEstudia;
    }
    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<String>();
        }
        return mCurrentName;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnChange = findViewById(R.id.ButtonChange);
        switchy = findViewById(R.id.simpleSwitch);
        switchyAprobar = findViewById(R.id.simpleSwitchAprobar);
        txtViewInfo = findViewById(R.id.textView);
        txtViewInfoAprobar = findViewById(R.id.textViewAprobar);
        txtTengoanos = findViewById(R.id.editTextTengoanos);
        etEdad = findViewById(R.id.etAge);
        // Create the observer which updates the UI.
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                BtnChange.setText(newName);
            }
        };
        BtnChange.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                String anotherName = "GALLETA! " + i;
                getCurrentName().setValue(anotherName);
            }
        });
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        getCurrentName().observe(this, nameObserver);
        final Observer<Boolean> ObserverTrabajo = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean bol) {
                if(bol == true){
                    txtViewInfo.setText("Trabaja en casa");
                }
                else{
                    txtViewInfo.setText("No trabaja en casa");
                }

            }
        };
        switchy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(activado == true){
                    getCurrentTrabajo().setValue(false);
                    activado = false;
                }
                else{
                    getCurrentTrabajo().setValue(true);
                    activado = true;
                }

            }
        });
        getCurrentTrabajo().observe(this, ObserverTrabajo);
        final Observer<Boolean> ObserverEstudio = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean bol) {
                if(bol == true){
                    txtViewInfoAprobar.setText("Voy a aprobar");
                }
                else{
                    txtViewInfoAprobar.setText("No voy a aprobar");
                }

            }
        };
        switchyAprobar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(activado == true){
                    getCurrentEstudio().setValue(false);
                    activado = false;
                }
                else{
                    getCurrentEstudio().setValue(true);
                    activado = true;
                }

            }
        });
        getCurrentEstudio().observe(this, ObserverEstudio);
       Observer<String> ObserverEdad = new Observer<String>() {
           @Override
           public void onChanged(@Nullable String s) {

               txtTengoanos.setText("Tengo " + s + " años");

               }

       };
        etEdad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String mAge =s.toString();
                age.postValue(mAge);

            }
        });
        getCurrentEdad().observe(this, ObserverEdad);
    }
}
