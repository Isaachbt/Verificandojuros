package com.curso.verificandojuros;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView resultadoJuros,valorOriginal,valorFinal,textDesconto,economiza;
    private EditText editValorJuros,editSegundoValor;
    private Spinner opcoesConta;
    private String itemSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inicializacao();
        validandoEditText();
        spinner();
    }

    public void inicializacao(){

        resultadoJuros = findViewById(R.id.textJuros);
        editValorJuros = findViewById(R.id.editValor);
        opcoesConta = findViewById(R.id.spinner);
        editSegundoValor = findViewById(R.id.editSegundoValor);
        valorFinal = findViewById(R.id.valorFInal);
        valorOriginal = findViewById(R.id.valorOriginal);
        textDesconto = findViewById(R.id.desconto);
        economiza = findViewById(R.id.economia);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        itemSelecionado = adapterView.getItemAtPosition(i).toString();
        tratandoDesconto();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void tratandoDesconto(){
        if (itemSelecionado.equals("%")){
            textDesconto.setText("Desconto(%off)");
            valorOriginal.setText("Valor original");
            resultadoJuros.setText("");
            editValorJuros.setText("");
            editSegundoValor.setText("");
        }else if(itemSelecionado.equals("X")){
            textDesconto.setText("Em quantas vezes");
            valorOriginal.setText("Qual valor?");
            resultadoJuros.setText("");
            editValorJuros.setText("");
            editSegundoValor.setText("");
            economiza.setText("");
        }else if (itemSelecionado.equals("/")){
            valorOriginal.setText("Qual valor?");
            textDesconto.setText("Dividido por");
            resultadoJuros.setText("");
            editValorJuros.setText("");
            editSegundoValor.setText("");
            economiza.setText("");
        }else if (itemSelecionado.equals("-")){
            valorOriginal.setText("Qual valor?");
            textDesconto.setText("Menos");
            resultadoJuros.setText("");
            editValorJuros.setText("");
            editSegundoValor.setText("");
            economiza.setText("");
        }else if (itemSelecionado.equals("+")){
            valorOriginal.setText("Qual valor?");
            textDesconto.setText("Mais");
            resultadoJuros.setText("");
            editValorJuros.setText("");
            editSegundoValor.setText("");
            economiza.setText("");
        }
    }

    private void spinner(){
        //dessa forma e criado um spinner
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.opcaoConta, R.layout.spinner_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        opcoesConta.setAdapter(adapter);
        opcoesConta.setOnItemSelectedListener(this);

    }

    private void validandoEditText(){

        editValorJuros.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!editValorJuros.getText().toString().isEmpty()){
                    conta();
                }else{
                    economiza.setText("");
                    resultadoJuros.setText("");
                    textDesconto.setText("");
                    valorFinal.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editSegundoValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!editSegundoValor.getText().toString().isEmpty()){
                    conta();
                }else{
                    economiza.setText("");
                    resultadoJuros.setText("");
                    textDesconto.setText("");
                    valorFinal.setText("");
                }



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void conta(){

        if (!editValorJuros.getText().toString().isEmpty()){
            if (!editSegundoValor.getText().toString().isEmpty()) {
                if (itemSelecionado.equals("%")) {
                    int y = Integer.parseInt(editValorJuros.getText().toString());
                    int porcentagem = Integer.parseInt(editSegundoValor.getText().toString());
                    int valorCalculado = (int) ((porcentagem * y)/100);//porcentagem
                    int sobra = valorCalculado - y;
                    String valorTotal = String.valueOf(valorCalculado);
                    String restoConta = String.valueOf(sobra);
                    valorFinal.setText("Valor final");
                    resultadoJuros.setText(restoConta);
                    economiza.setText("Economiza "+valorTotal);
                } else if (itemSelecionado.equals("X")) {
                    int valor1 = Integer.parseInt(editValorJuros.getText().toString()) *
                            Integer.parseInt(editSegundoValor.getText().toString());
                    String valorTotal = String.valueOf( valor1+"x");
                    valorFinal.setText("Valor final");
                    resultadoJuros.setText(valorTotal);
                } else if (itemSelecionado.equals("/")) {
                    int valor1 = Integer.parseInt(editValorJuros.getText().toString()) /
                            Integer.parseInt(editSegundoValor.getText().toString());
                    String valorTotal = String.valueOf( valor1);
                    valorFinal.setText("Valor final");
                    resultadoJuros.setText(valorTotal);
                }else if (itemSelecionado.equals("-")){
                    int valor1 = Integer.parseInt(editValorJuros.getText().toString()) -
                            Integer.parseInt(editSegundoValor.getText().toString());
                    String valorTotal = String.valueOf(valor1);
                    valorFinal.setText("Valor final");
                    resultadoJuros.setText(valorTotal);
                }else if (itemSelecionado.equals("+")){
                    int valor1 = Integer.parseInt(editValorJuros.getText().toString()) +
                            Integer.parseInt(editSegundoValor.getText().toString());
                    String valorTotal = String.valueOf(valor1);
                    valorFinal.setText("Valor final");
                    resultadoJuros.setText(valorTotal);
                }
            }
        }else{
            // Toast.makeText(getApplicationContext(),"Preencha os campos",Toast.LENGTH_SHORT).show();
        }
    }

}