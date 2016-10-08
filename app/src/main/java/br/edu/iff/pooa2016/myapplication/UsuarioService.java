package br.edu.iff.pooa2016.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Heloy Pereira on 06/10/2016.
 */
public class UsuarioService {

    private SQLiteDatabase db_reader, db_writer;
    private BDHelper db;

        public UsuarioService(Context context){
            db = new BDHelper(context);
            db_reader = db.getReadableDatabase();
            db_writer = db.getWritableDatabase();
        }

        public void saveUsuario(Usuario usuario){
            ContentValues values = new ContentValues();
            values.put(BDHelper.NOME, usuario.getNome());
            values.put(BDHelper.EMAIL, usuario.getEmail());
            values.put(BDHelper.SENHA, usuario.getSenha());
            values.put(BDHelper.TIPO, usuario.getTipo());

            db_writer.insert(BDHelper.TABLE_USUARIOS, null, values);
        }

        public void updateUsuario(Usuario usuario){
            ContentValues values = new ContentValues();
            values.put(BDHelper.NOME, usuario.getNome());
            values.put(BDHelper.EMAIL, usuario.getEmail());
            values.put(BDHelper.SENHA, usuario.getSenha());
            values.put(BDHelper.TIPO, usuario.getTipo());

            db_writer.update(BDHelper.TABLE_USUARIOS, values, BDHelper.ID+" = "+String.valueOf(usuario.getId()),null);
        }

        public void deleteUsuario(Usuario usuario){
            db_writer.delete(BDHelper.TABLE_USUARIOS, BDHelper.ID+" = "+String.valueOf(usuario.getId()),null);
        }

        public Usuario getUsuario(String email){
            String sql = ("SELECT * FROM "+BDHelper.TABLE_USUARIOS+" WHERE "+BDHelper.EMAIL+" = '"+email+"';");
            Usuario usuario = new Usuario();
            Cursor cursor = db_reader.rawQuery(sql, null, null);

            if (cursor.getCount()>0) {
                cursor.moveToFirst();
                usuario.setId(cursor.getInt(0));
                usuario.setNome(cursor.getString(1));
                usuario.setEmail(cursor.getString(2));
                usuario.setSenha(cursor.getString(3));
                usuario.setTipo(cursor.getString(4));
            }

            return usuario;
        }

        public String login(String email, String senha){
            String sql = ("SELECT "+BDHelper.EMAIL+", "+BDHelper.SENHA+", "+BDHelper.TIPO+"  FROM "+BDHelper.TABLE_USUARIOS+" WHERE "+BDHelper.EMAIL+" = '"+email+"';");

            Cursor cursor = db_reader.rawQuery(sql, null, null);

            if (cursor.getCount()>0) {
                cursor.moveToFirst();


                if (email.equals(cursor.getString(0))){
                    if (senha.equals(cursor.getString(1))){
                        return cursor.getString(2);
                    }
                }
            }

            return "false";
        }

}
