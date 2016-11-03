package com.example.helio.bancodedados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListarPousada extends AppCompatActivity implements AdapterView.OnItemClickListener{

    SimpleCursorAdapter Adapter;
    Cursor cursor;
    SQLiteDatabase db;
    ListView ListarPousadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pousada);

        BuscarPousada();
        ListaPousada();
        ListarPousadas.setOnItemClickListener(this);

    }



    public void ListaPousada(){


        try {

            ListarPousadas = (ListView) findViewById(R.id.Listapousada);

            String[] from = {"nomePousada", "bairroPousada", "telefonePousada"};
            int[] to = {R.id.textVieNomePousada, R.id.textVieBairroPousada, R.id.textVieTelefonePousada};

            Adapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.mostrarpousada, cursor, from, to);

            ListarPousadas.setAdapter(Adapter);

            Toast.makeText(getApplicationContext(),"Finalizando  Listagem de Pousada",Toast.LENGTH_SHORT).show();

            //MessageAlerta("Sucesso de listagem ","Pousada  Listada");
        }catch (Exception ex){

            MessageAlerta("Problema de Listagem"," ERROU");

        }
    }

    public void BuscarPousada(){

       // SQLiteDatabase db = null;

        try {
            db = openOrCreateDatabase("Pousada.db", Context.MODE_PRIVATE, null);
            cursor = db.rawQuery("Select * from Pousada", null);

            MessageAlerta("Sucesso", "Buscando Pousada");

        }catch (Exception ex) {

            MessageAlerta("Pousada", "Não Buscouu");
        }

    }
    public void MessageAlerta(String TituloAlerta, String MesagenAlerta) {

        AlertDialog.Builder Mensagen = new AlertDialog.Builder(ListarPousada.this);
        Mensagen.setMessage(MesagenAlerta);
        Mensagen.setTitle(TituloAlerta);
        Mensagen.setNeutralButton("OK", null);
        Mensagen.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        SQLiteCursor cursor = (SQLiteCursor) Adapter.getItem(position);
        String nomePousada = cursor.getString(cursor.getColumnIndex("nomePousada"));
        String bairro = cursor.getString(cursor.getColumnIndex("bairroPousada"));
        // startActivity(new Intent(getApplicationContext(),Inten).));

        Toast.makeText(getApplicationContext(),"  selecionou o nome  :"+nomePousada+ "  Selecionou o Bairro "+bairro,Toast.LENGTH_SHORT).show();


    }
}
