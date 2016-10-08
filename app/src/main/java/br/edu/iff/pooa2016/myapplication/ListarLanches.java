package br.edu.iff.pooa2016.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListarLanches extends AppCompatActivity {

    private List<Lanche> lista;
    private ListView LV_listaLanches;
    private ArrayList<String> NomeLanches;
    private ArrayAdapter<String> listaLanches;
    private BDAdapter db;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_lanches);

        Bundle usuario = getIntent().getExtras();
        email = usuario.getString("email");

        db = new BDAdapter(this);
        NomeLanches = new ArrayList<>();
        lista = db.getLanches();

        if (lista != null) {
            for (int i = 0; i<lista.size(); i++) {
                NomeLanches.add(lista.get(i).getNome());
            }
        }

        LV_listaLanches = (ListView) findViewById(R.id.listaLanches);

        listaLanches = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, NomeLanches);
        LV_listaLanches.setAdapter(listaLanches);

        LV_listaLanches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent tela = new Intent(ListarLanches.this, LancheView.class);
                Bundle lanche = new Bundle();
                lanche.putInt("id", lista.get(position).getId());
                lanche.putString("nome", lista.get(position).getNome());
                lanche.putString("tipo", lista.get(position).getTipo());
                lanche.putString("descricao", lista.get(position).getDescricao());
                lanche.putFloat("preco", lista.get(position).getPreco());
                lanche.putString("email", email);
                tela.putExtras(lanche);
                startActivity(tela);
            }
        });


    }
}

