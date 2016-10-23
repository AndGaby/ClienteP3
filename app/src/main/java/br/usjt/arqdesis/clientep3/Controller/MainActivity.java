package br.usjt.arqdesis.clientep3.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.arqdesis.clientep3.Model.Cliente;
import br.usjt.arqdesis.clientep3.Model.ClienteRequester;
import br.usjt.arqdesis.clientep3.R;


public class MainActivity extends AppCompatActivity {
    private EditText nome;
    public static final String LISTA = " br.usjt.arqdesis.clientep3.chave";
    public static final String SERVIDOR = "10.128.92.186";
    ClienteRequester requester;
    String chave;
    Intent intent;
    Cliente[] lista = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = (EditText)findViewById(R.id.busca_nome_cliente);

    }

    public void buscarClientes(View view){
        requester = new ClienteRequester();
        chave = nome.getText().toString();
        intent = new Intent(this, ListaClientesActivity.class);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    lista = requester.getClientes(SERVIDOR + "url vai aqui");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        intent.putExtra(LISTA, lista);
                        startActivity(intent);
                    }
                });
            }
        }).start();
    }
}
