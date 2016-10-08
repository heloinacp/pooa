package br.edu.iff.pooa2016.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroUsuario extends AppCompatActivity {

        private EditText ET_nome, ET_email, ET_senha,ET_tipo;
        private Button salvar, cancelar, deletar;
        private UsuarioService usuarioService;
        private String id;
        private Usuario usuario;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cadastro_usuario);

            usuarioService = new UsuarioService(this);
            deletar = (Button) findViewById(R.id.BT_deletar);

            usuario = new Usuario();

            try {
                Bundle objeto = getIntent().getExtras();
                Usuario B_usuario = usuarioService.getUsuario(objeto.getString("email"));

                id = String.valueOf(B_usuario.getId());
                ET_nome.setText(B_usuario.getNome());
                ET_email.setText(B_usuario.getEmail());
                ET_senha.setText(B_usuario.getSenha());
                ET_tipo.setText(B_usuario.getTipo());

            } catch (Exception e) {
                deletar.setEnabled(false);
                deletar.setVisibility(deletar.INVISIBLE);
                id = null;
            }

            ET_nome = (EditText) findViewById(R.id.ET_nome);
            ET_email = (EditText) findViewById(R.id.ET_email);
            ET_senha = (EditText) findViewById(R.id.ET_senha);
            ET_tipo = (EditText) findViewById(R.id.ET_tipo);

            salvar = (Button) findViewById(R.id.BT_salvar);
            cancelar = (Button) findViewById(R.id.BT_cancelar);

            salvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usuario.setNome(ET_nome.getText().toString());
                    usuario.setEmail(ET_email.getText().toString());
                    usuario.setSenha(ET_senha.getText().toString());
                    usuario.setTipo(ET_tipo.getText().toString());
                    if (id != null) {
                        usuario.setId(Integer.parseInt(id));
                        usuarioService.updateUsuario(usuario);
                    } else {
                        usuarioService.saveUsuario(usuario);
                        finish();
                    }
                }
            });

            deletar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usuarioService.deleteUsuario(usuario);
                    finish();
                }
            });

            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }
    }
