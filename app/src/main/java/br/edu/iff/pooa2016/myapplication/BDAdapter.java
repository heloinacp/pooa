package br.edu.iff.pooa2016.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heloy Pereira on 06/10/2016.
 */
public class BDAdapter {
    private SQLiteDatabase db_reader, db_writer;
    private BDHelper db;

    public BDAdapter(Context context){
        db = new BDHelper(context);
        db_reader = db.getReadableDatabase();
        db_writer = db.getReadableDatabase();
    }

    //Metodo para salvar um unico lanche:
    public void save_lanche(Lanche lanche){
        ContentValues values = new ContentValues();
        values.put(BDHelper.NOME, lanche.getNome());
        values.put(BDHelper.TIPO, lanche.getTipo());
        values.put(BDHelper.DESCRICAO, lanche.getDescricao());
        values.put(BDHelper.PRECO, lanche.getPreco());
        values.put(BDHelper.QUANTIDADE, lanche.getQuantidade());

        db_writer.insert(BDHelper.TABLE_LANCHES, null, values);

        Log.i("DEB", "Foi inserido o lanche!");
    }

    //Metodo para pegar a lista de todos os lanches:
    public List getLanches(){
        List<Lanche> lanches =  new ArrayList<>();
        // Linha de SQL para fazer a pesquisa:
        String sql = "SELECT * FROM "+BDHelper.TABLE_LANCHES+";";

        //Faz a busca e retorna um tipo Cursor
        Cursor cursor = db_reader.rawQuery(sql, null, null);

        if (cursor.getCount()>0) {
            //Move o cursor ao primeiro item da lista:
            cursor.moveToFirst();

            do {
                //Cria um objeto lanche:
                Lanche lanche = new Lanche();

                //Preenche o objeto lanche:
                lanche.setId(Integer.parseInt(cursor.getString(0)));
                lanche.setNome(cursor.getString(1));
                lanche.setTipo(cursor.getString(2));
                lanche.setDescricao(cursor.getString(3));
                lanche.setPreco(Float.parseFloat(cursor.getString(4)));
                lanche.setQuantidade(Integer.parseInt(cursor.getString(5)));

                //Adiciona o objeto lanche na lista:
                lanches.add(lanche);

                //Executa enquanto há um proximo
            } while (cursor.moveToNext());
        }else{
            return null;
        }

        //Retorna a Lista:
        return lanches;
    }

    //Metodo para pegar a lista de lanches por tipo:
    public List getTipoLanches(String tipo){
        List<Lanche> lanches =  new ArrayList<>();
        // Linha de SQL para fazer a pesquisa:
        String sql = "SELECT * FROM "+BDHelper.TABLE_LANCHES+";";

        //Faz a busca e retorna um tipo Cursor
        Cursor cursor = db_reader.rawQuery(sql, null, null);

        if (cursor.getCount()>0) {
            //Move o cursor ao primeiro item da lista:
            cursor.moveToFirst();

            do {
                if (cursor.getString(2) == tipo) {
                    //Cria um objeto lanche:
                    Lanche lanche = new Lanche();

                    //Preenche o objeto lanche:
                    lanche.setId(Integer.parseInt(cursor.getString(0)));
                    lanche.setNome(cursor.getString(1));
                    lanche.setTipo(cursor.getString(2));
                    lanche.setDescricao(cursor.getString(3));
                    lanche.setPreco(Float.parseFloat(cursor.getString(4)));
                    lanche.setQuantidade(Integer.parseInt(cursor.getString(5)));

                    //Adiciona o objeto lanche na lista:
                    lanches.add(lanche);
                }
                //Executa enquanto há um proximo
            } while (cursor.moveToNext());
        }else{
            return null;
        }

        //Retorna a Lista:
        return lanches;
    }

    //Metodo para deletar um lanche:
    public void delete(Lanche lanche){
        String sql = "DELETE FROM "+BDHelper.TABLE_LANCHES+" WHERE "+BDHelper.ID+" = "+String.valueOf(lanche.getId())+";";
        db_writer.execSQL(sql);
    }

    //Metodo para deletar um lanche
    public void update_lanche(Lanche lanche){
        ContentValues values = new ContentValues();
        values.put(BDHelper.NOME, lanche.getNome());
        values.put(BDHelper.TIPO, lanche.getTipo());
        values.put(BDHelper.DESCRICAO, lanche.getDescricao());
        values.put(BDHelper.PRECO, lanche.getPreco());
        values.put(BDHelper.QUANTIDADE, lanche.getQuantidade());

        db_writer.update(BDHelper.TABLE_LANCHES, values, BDHelper.ID+" = "+lanche.getId(),null );

        Log.i("DEB", "Foi inserido o lanche!");
    }
}

