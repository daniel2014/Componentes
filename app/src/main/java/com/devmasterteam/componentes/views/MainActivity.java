package com.devmasterteam.componentes.views;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devmasterteam.componentes.mock.Mock;
import com.devmasterteam.componentes.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa elementos
        this.mViewHolder.mButtonToastMe = (Button) this.findViewById(R.id.button_toast_me);
        this.mViewHolder.mButtonSnackMe = (Button) this.findViewById(R.id.button_snack_me);
        this.mViewHolder.mSpinnerDynamic = (Spinner) this.findViewById(R.id.spinner_dynamic);
        this.mViewHolder.mButtonProgress = (Button) this.findViewById(R.id.button_progress);
        this.mViewHolder.mButtonGetSpinner = (Button) this.findViewById(R.id.button_get_spinner);
        this.mViewHolder.mButtonSetSpinner = (Button) this.findViewById(R.id.button_set_spinner);
        this.mViewHolder.mButtonGetSeek = (Button) this.findViewById(R.id.button_get_seek);
        this.mViewHolder.mButtonSetSeek = (Button) this.findViewById(R.id.button_set_seek);
        this.mViewHolder.mSeekValue = (SeekBar) this.findViewById(R.id.seekbar_seek);
        this.mViewHolder.mConstraintLayout = (ConstraintLayout) this.findViewById(R.id.constraint_layout);
        this.mViewHolder.mTextValue = (TextView) this.findViewById(R.id.text_seek_value);
        this.mViewHolder.mProgress = new ProgressDialog(this);

        // Carrega Spinner Dinâmico
        this.loadSpinner();

        // Seta cliques
        this.setListeners();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.button_toast_me) {

            // Toast.makeText(this, "Toast me - hardcode", Toast.LENGTH_SHORT).show();
            Toast toast = Toast.makeText(this, R.string.toast_me, Toast.LENGTH_LONG);

            // Não deixa as bordas do toast arredondadas
            // View view = toast.getView();
            //view.setBackgroundColor(Color.BLACK);

            // Inflamos um layout criado especificamente para a toast
            LayoutInflater inflater = getLayoutInflater();
            View toastLayout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.custom_toast_layout));
            toast.setView(toastLayout);

            // Cor do texto
            // TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
            TextView textView = (TextView) toast.getView().findViewById(R.id.custom_toast_message);
            textView.setTextColor(Color.YELLOW);

            toast.show();


        } else if (id == R.id.button_snack_me) {

            // Snackbar.make(this.mViewHolder.mConstraintLayout, "Snack - hardcode", Snackbar.LENGTH_LONG).show();
            Snackbar snackbar = Snackbar.make(this.mViewHolder.mConstraintLayout, R.string.snack_me, Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.BLUE);
            snackbar.setAction("DESFAZER", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(mViewHolder.mConstraintLayout, "Message is restored!", Snackbar.LENGTH_SHORT).show();
                }
            });

            // Mudando a cor do plano de fundo
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));

            // Mudando a cor do texto
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            textView.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));

            // Deprecated
            getResources().getColor(R.color.colorPrimary);

            // Mostra a snack
            snackbar.show();

        } else if (id == R.id.button_get_spinner) {

            String value = String.valueOf(this.mViewHolder.mSpinnerDynamic.getSelectedItem().toString());
            // String value = String.valueOf(this.mViewHolder.mSpinnerDynamic.getSelectedItemId());
            // String value = String.valueOf(this.mViewHolder.mSpinnerDynamic.getSelectedItemPosition());
            Toast.makeText(this, value, Toast.LENGTH_LONG).show();

        } else if (id == R.id.button_set_spinner) {

            // Atribui o valor do spinner de acordo com o ID do elemento
            this.mViewHolder.mSpinnerDynamic.setSelection(3);

        } else if (id == R.id.button_progress) {

            this.mViewHolder.mProgress.setTitle("Título");
            this.mViewHolder.mProgress.setMessage("Minha mensagem");
            this.mViewHolder.mProgress.show();

        } else if (id == R.id.button_get_seek) {

            String value = String.valueOf(this.mViewHolder.mSeekValue.getProgress());
            Toast.makeText(this, value, Toast.LENGTH_LONG).show();

        } else if (id == R.id.button_set_seek) {

            this.mViewHolder.mSeekValue.setProgress(10);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String value = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "NOTHING", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.seekbar_seek) {
            this.mViewHolder.mTextValue.setText(String.valueOf(progress));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /**
     * Atribui eventos aos elementos
     */
    private void setListeners() {
        this.mViewHolder.mButtonToastMe.setOnClickListener(this);
        this.mViewHolder.mButtonSnackMe.setOnClickListener(this);
        this.mViewHolder.mButtonGetSpinner.setOnClickListener(this);
        this.mViewHolder.mButtonSetSpinner.setOnClickListener(this);
        this.mViewHolder.mButtonGetSeek.setOnClickListener(this);
        this.mViewHolder.mButtonSetSeek.setOnClickListener(this);
        this.mViewHolder.mButtonProgress.setOnClickListener(this);

        // Spinner
        this.mViewHolder.mSpinnerDynamic.setOnItemSelectedListener(this);

        // Seekbar
        this.mViewHolder.mSeekValue.setOnSeekBarChangeListener(this);
    }

    /**
     * Carrega spinner dinâmico
     */
    private void loadSpinner() {

        // Carrega dados
        List<String> lst = Mock.spinnerMock();

        // Cria o adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lst);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Atribui o adapter ao spinner
        this.mViewHolder.mSpinnerDynamic.setAdapter(adapter);

    }

    /**
     * ViewHolder
     */
    private static class ViewHolder {
        private Button mButtonToastMe;
        private Button mButtonSnackMe;
        private Spinner mSpinnerDynamic;
        private Button mButtonGetSpinner;
        private Button mButtonSetSpinner;
        private Button mButtonProgress;
        private Button mButtonGetSeek;
        private Button mButtonSetSeek;
        private SeekBar mSeekValue;
        private ProgressDialog mProgress;
        private TextView mTextValue;
        private ConstraintLayout mConstraintLayout;
    }
}
