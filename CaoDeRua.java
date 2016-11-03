package com.example.helio.bancodedados;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsSpinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helio.bancodedados.Dao.Caocadastro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CaoDeRua extends AppCompatActivity {

    SQLiteDatabase db;
    SimpleCursorAdapter cursor;
    Spinner snOptar;
    private TextView TextNomedoCao;
    private  TextView textRaca;
    private TextView Emaildodono;
    private ImageButton caoButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_de_rua);

        Criartabela();

        TextNomedoCao = (TextView)findViewById(R.id.editTextNomeCao);
        textRaca = (TextView)findViewById(R.id.ediTextRacaCao);
        Emaildodono = (TextView)findViewById(R.id.editEmailDoDono);
        snOptar = (Spinner) findViewById(R.id.spinner);
        caoButton = (ImageButton)findViewById(R.id.imageButtonAvancarCadastraCao);


        List<String> opcao = new ArrayList<>();
        opcao.add("PEQUENO  10 - 25 cm");
        opcao.add("MEDIO 26 - 38 cm");
        opcao.add(" GRANDE 38 - ACIMA cm");


        //String[] opcao={"PEQUENO ", ",};

        ArrayAdapter<String> Adapters = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, opcao);
        snOptar.setAdapter(Adapters);

        caoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Caocadastro cad = new Caocadastro();
                    cad.setNomeCao(TextNomedoCao.getText().toString());
                    cad.setRaca(textRaca.getText().toString());
                    cad.setEmailDoDonoCao(Emaildodono.getText().toString());

                    InsercaoCao(cad);


                    MessageAlerta("Sucesso de insercao", "Excelente");

                }catch (Exception ex){

                    MessageAlerta("EXCESSÃO DE ERROR", "PROBLEMAS");

                }finally {


                }
                db.close();
            }

        });
    }




    public void Criartabela(){

        SQLiteDatabase db = null;

        try{

            db = openOrCreateDatabase("Cachorro.db", Context.MODE_PRIVATE,null);
             StringBuilder sql = new StringBuilder();
            sql.append("CREATE TABLE IF NOT EXISTS Cachorro(");
            sql.append("_id integer primary key autoincrement,");
            sql.append("nomeCao varchar (100),");
            sql.append("racaCao varchar(30),");
            sql.append("emaildodonoCao varchar(120))");

            db.execSQL(sql.toString());

            MessageAlerta("Sucesso Programador","Excelente uhuuh");



        }catch (Exception ex){

            MessageAlerta("Deu bugg de banco","Excelente uhuuh");
        }finally {
            Toast.makeText(getApplicationContext(),"Fechando na criacâo",Toast.LENGTH_SHORT).show();

        }
       // db.close();

    }

    public void MessageAlerta(String TituloAlerta, String MesagenAlerta) {

        AlertDialog.Builder Mensagen = new AlertDialog.Builder(CaoDeRua.this);
        Mensagen.setMessage(MesagenAlerta);
        Mensagen.setTitle(TituloAlerta);
        Mensagen.setNeutralButton("OK", null);
        Mensagen.show();
    }

    public  void InsercaoCao(Caocadastro cad){


        try {
            db = openOrCreateDatabase("Cachorro.db",Context.MODE_PRIVATE,null);
            ContentValues contentValues = new ContentValues();

            contentValues.put("nomeCao",cad.getNomeCao());
            contentValues.put("racaCao",cad.getRaca());
            contentValues.put("emaildodonoCao",cad.getEmailDoDonoCao());
           // valores.put("opcao",cad.getTamanho());

            db.insert("Cachorro",null, contentValues);

            TextNomedoCao.setText("");
            textRaca.setText("");
            Emaildodono.setText("");

            Toast.makeText(getApplicationContext(),"sucesso na Insercao",Toast.LENGTH_SHORT).show();

            MessageAlerta("Muito Bom","Sucesso ");


        }catch (Exception ex){
            Toast.makeText(getApplicationContext(),"Problemas na Insercao",Toast.LENGTH_SHORT).show();

        }finally {

            Toast.makeText(getApplicationContext(),"fechando",Toast.LENGTH_SHORT).show();

            db.close();
        }

    }

}
