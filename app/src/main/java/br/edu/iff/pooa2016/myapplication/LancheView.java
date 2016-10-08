package br.edu.iff.pooa2016.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LancheView extends AppCompatActivity {

    private int id;
    private float preco;
    private String nome, descricao, tipo;

    private TextView TV_Descricao, TV_Preco;
    private Button Apagar, Editar;
    private String email;
    private Usuario usuario;
    private UsuarioService usuarioService;

    private BDAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanche_view);

        usuarioService = new UsuarioService(this);

        //Recebendo as informações de outra tela:
        Bundle lanche = getIntent().getExtras();
        id = lanche.getInt("id");
        nome = lanche.getString("nome");
        tipo = lanche.getString("tipo");
        descricao = lanche.getString("descricao");
        preco = lanche.getFloat("preco");

        email = lanche.getString("email");
        usuario = usuarioService.getUsuario(email);

        db = new BDAdapter(this);

        setTitle(tipo + " " + nome);

        TV_Descricao = (TextView) findViewById(R.id.Descricao);
        TV_Preco = (TextView) findViewById(R.id.Preco);

        Editar = (Button) findViewById(R.id.BT_editar);
        Apagar = (Button) findViewById(R.id.BT_ApagarLanche);

        Log.i("DEB", usuario.getTipo());

        if (!usuario.getTipo().equals("admin")){
            Editar.setEnabled(false);
            Editar.setVisibility(Editar.INVISIBLE);

            Apagar.setEnabled(false);
            Apagar.setVisibility(Apagar.INVISIBLE);
        }

        TV_Descricao.setText(descricao);
        TV_Preco.setText(String.format("R$ %.2f", preco));

        Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Passando informações do objeto para a outra tela:
                Intent tela = new Intent(LancheView.this, Cadastrarlanche.class);
                Bundle lanche = new Bundle();
                lanche.putInt("id", id);
                lanche.putString("nome", nome);
                lanche.putString("tipo", tipo);
                lanche.putString("descricao", descricao);
                lanche.putFloat("preco", preco);
                tela.putExtras(lanche);
                startActivity(tela);
                finish();
            }
        });

        Apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lanche lanche = new Lanche();
                lanche.setId(id);
                lanche.setNome(nome);
                lanche.setTipo(tipo);
                lanche.setDescricao(descricao);
                lanche.setPreco(preco);
                db.delete(lanche);
                finish();
            }
        });



    }
}
