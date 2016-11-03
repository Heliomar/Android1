package com.example.helio.bancodedados;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ListarCao extends AppCompatActivity {

    ListView listaDeCao;
    SQLiteDatabase db;
    SimpleCursorAdapter Adapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cao);


       // listaDeCao.setOnClickListener(this);
       BuscaCao();
       ListaCao();


    }
    public void BuscaCao(){
        //SQLiteDatabase db = null;

        try {

            db = openOrCreateDatabase("Cachorro.db", Context.MODE_PRIVATE, null);
            cursor = db.rawQuery("Select * from Cachorro", null);

           // Toast.makeText(getApplicationContext(),"Sucesso para cao",Toast.LENGTH_SHORT).show();

        }catch (Exception ex){

            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();



        }

    }
    public void ListaCao(){


        try {

            listaDeCao = (ListView) findViewById(R.id.listaCaodeRua);

            String [] from = {"nomeCao", "racaCao", "emaildodonoCao"};
            int[] to = {R.id.textVieNomeCao, R.id.textVRaça, R.id.textViEmaildoDono};

            Adapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.mostrarcaoderua, cursor, from, to);
            listaDeCao.setAdapter(Adapter);

            Toast.makeText(getApplicationContext(),"Sucesso de cao",Toast.LENGTH_SHORT).show();

        }catch (Exception ex){

            Toast.makeText(getApplicationContext(),"ERROR DE LISTAGEM",Toast.LENGTH_SHORT).show();
        }



    }
}
