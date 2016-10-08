package br.edu.iff.pooa2016.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Login extends AppCompatActivity {

    private EditText email, senha;
    private Button cadastrar, entrar;
    private UsuarioService usuarioService;
    //private String login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioService = new UsuarioService(this);

        email = (EditText) findViewById(R.id.ET_email);
        senha = (EditText) findViewById(R.id.ET_senha);


        cadastrar = (Button) findViewById(R.id.BT_cadastrar);
        entrar = (Button) findViewById(R.id.BT_login);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = usuarioService.login(email.getText().toString(), senha.getText().toString());
                if (login.equals("admin")) {
                    Intent tela = new Intent(Login.this, MainAdminActivity.class);
                    Bundle usuario = new Bundle();
                    usuario.putString("email", email.getText().toString());
                    tela.putExtras(usuario);
                    startActivity(tela);
                    finish();
                }
                else {
                    if (login.equals("user")) {
                        Intent tela = new Intent(Login.this, MainActivity.class);
                        Bundle usuario = new Bundle();
                        usuario.putString("email", email.getText().toString());
                        tela.putExtras(usuario);
                        startActivity(tela);
                        finish();
                    }
                }
            }

        });


        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela = new Intent(Login.this, CadastroUsuario.class);
                startActivity(tela);
            }
        });

    }
}