package br.usjt.arqdesis.clientep3.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.arqdesis.clientep3.Model.ClienteRequester;
import br.usjt.arqdesis.clientep3.R;
import br.usjt.arqdesis.clientep3.Model.Cliente;
import br.usjt.arqdesis.clientep3.Model.ClienteAdapter;

public class ListaClientesActivity extends AppCompatActivity {
    public static final String NOME = "br.usjt.arqdesis.clientep2.nome";
    public static final String EMAIL = "br.usjt.arqdesis.clientep2.email";
    public static final String FONE = "br.usjt.arqdesis.clientep2.fone";
    Activity atividade;
    Cliente[] lista;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lista_clientes);
            atividade = this;
            Intent intent = getIntent();
            ClienteRequester requester = new ClienteRequester();
            ArrayList<Cliente> clientes = (ArrayList<Cliente>) intent.getSerializableExtra(MainActivity.LISTA);
            lista = clientes.toArray(new Cliente[0]);

            BaseAdapter adapter = new ClienteAdapter(this, lista);
            ListView listView = (ListView) findViewById(R.id.lista_clientes);
            listView.setAdapter(adapter);


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                BaseAdapter adapter = new ClienteAdapter(this, lista);
        ListView listView = (ListView) findViewById(R.id.lista_clientes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
                Intent intent = new Intent(atividade, DetalheClienteActivity.class);
                intent.putExtra(NOME, lista[position].getNome());
                intent.putExtra(FONE, lista[position].getFone());
                intent.putExtra(EMAIL, lista[position].getEmail());

                startActivity(intent);

            }

        });
    }
}
