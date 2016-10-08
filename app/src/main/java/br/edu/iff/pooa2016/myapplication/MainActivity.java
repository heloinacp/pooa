package br.edu.iff.pooa2016.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button Listar, Cadastrar;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle usuario = getIntent().getExtras();
        email = usuario.getString("email");

        Listar = (Button) findViewById(R.id.BT_listarLanche);

        Listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela = new Intent(MainActivity.this, ListarLanches.class);
                Bundle usuario = new Bundle();
                usuario.putString("email", email);
                tela.putExtras(usuario);
                startActivity(tela);
            }
        });



    }
}