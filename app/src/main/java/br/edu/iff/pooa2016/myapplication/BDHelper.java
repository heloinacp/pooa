package br.edu.iff.pooa2016.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Heloy Pereira on 06/10/2016.
 */
public class BDHelper extends SQLiteOpenHelper {

    //Define o nome do banco de dados:
    public static final String DATABASE_NAME = "Teste_DB";

    //Define as tabelas que devem existir no banco de dados:
    public static final String TABLE_LANCHES = "lanches";
    public static final String TABLE_USUARIOS = "usuario";

    //Define a versao atual do banco de dados (Usado para testes de versao):
    public static final int DATABASE_VERSION = 4;

    //Define as colunas das tabelas:
    public static final String ID = "_id";
    public static final String NOME = "nome", EMAIL = "email", SENHA = "senha";
    public static final String TIPO = "tipo";
    public static final String DESCRICAO = "descricao";
    public static final String PRECO = "preco";
    public static final String QUANTIDADE = "quantidade";

    //Define a criação das tabelas:
    private static final String CRIAR_TABELA_LANCHES = "CREATE TABLE "+TABLE_LANCHES+"("+ID+" integer primary key autoincrement, "+NOME+" varchar(50) not null, "+TIPO+" varchar(50) not null, "+DESCRICAO+" varchar(150) not null, "+PRECO+" float not null, "+QUANTIDADE+" integer not null);";
    private static final String CRIAR_TABELA_USUARIOS = "CREATE TABLE "+TABLE_USUARIOS+" ("+ID+" integer primary key not null, "+
            NOME+" VARCHAR(50) not null, "+EMAIL+" VARCHAR(70) not null, "+SENHA+" VARCHAR(16) not null, "+TIPO+" VARCHAR(16) not null);";


    public BDHelper(Context context){
        //Inicia o banco de dados:
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Cria as tabelas:
        db.execSQL(CRIAR_TABELA_LANCHES);
        db.execSQL(CRIAR_TABELA_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Destroi as tabelas caso a versao mude:
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_LANCHES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USUARIOS);

        //Recria as tabelas:
        onCreate(db);
    }
}

