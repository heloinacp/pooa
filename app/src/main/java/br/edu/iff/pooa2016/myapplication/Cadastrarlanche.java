package br.edu.iff.pooa2016.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Cadastrarlanche extends AppCompatActivity {

    private EditText ET_nome, ET_tipo, ET_descricao, ET_valor;
    private Button BT_salvar;
    private BDAdapter db;

    private float preco;
    private String nome, descricao, tipo, id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrarlanche);

        //Iniciando o banco de dados
        db = new BDAdapter(this);

        ET_nome = (EditText) findViewById(R.id.ET_nome);
        ET_tipo = (EditText) findViewById(R.id.ET_tipo);
        ET_descricao = (EditText) findViewById(R.id.ET_descricao);
        ET_valor = (EditText) findViewById(R.id.ET_valor);

        BT_salvar = (Button) findViewById(R.id.BT_salvar);


        try {
            Bundle lanche = getIntent().getExtras();
            id = String.valueOf(lanche.getInt("id"));
            nome = lanche.getString("nome");
            tipo = lanche.getString("tipo");
            descricao = lanche.getString("descricao");
            preco = lanche.getFloat("preco");


            ET_nome.setText(nome);
            ET_tipo.setText(tipo);
            ET_descricao.setText(descricao);
            ET_valor.setText(String.valueOf(preco));
        } catch (Exception e) {
            id = null;
        }

        BT_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Criando um objeto lanche:
                Lanche lanche = new Lanche();

                //Preenchendo o objeto lanche:

                lanche.setNome(ET_nome.getText().toString());
                lanche.setTipo(ET_tipo.getText().toString());
                lanche.setDescricao(ET_descricao.getText().toString());
                lanche.setPreco(Float.parseFloat(ET_valor.getText().toString()));

                if (id != null) {
                    lanche.setId(Integer.parseInt(id));
                    //Fazendo Update no DB:
                    db.update_lanche(lanche);
                } else {
                    //Salvando no banco de dados:
                    db.save_lanche(lanche);
                }

                finish();
            }
        });
    }
}