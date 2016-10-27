package com.example.helio.bancodedados;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class Listar extends AppCompatActivity implements AdapterView.OnItemClickListener {


    SimpleCursorAdapter AdapterLista;
    Cursor cursor;
    SQLiteDatabase db;
    ListView listViewListar;
    BDcore BD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);


        BuscaDados();
        Listagem();



    }
    public void BuscaDados(){

        try{

            db = openOrCreateDatabase("DonoCao.db",Context.MODE_PRIVATE,null);
            cursor = db.rawQuery("SELECT * from  DonoCao",null);


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"Problema na busca de dados",Toast.LENGTH_SHORT).show();


        }finally {
            Toast.makeText(getApplicationContext(),"Finalizando com Busca de dados",Toast.LENGTH_SHORT).show();


        }
    }

    public void Listagem() {

        try {

            listViewListar = (ListView) findViewById(R.id.listView40);

            String[] from ={"nome", "telefone", "email"};
            int[] to ={R.id.nomeTex, R.id.editTelefone, R.id.textemail};

            AdapterLista = new SimpleCursorAdapter(getApplicationContext(), R.layout.mostrardadosbanco, cursor, from, to);
            listViewListar.setAdapter(AdapterLista);


            listViewListar.setOnItemClickListener(this);



        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"problemas de listagens",Toast.LENGTH_SHORT).show();


        }finally {

            Toast.makeText(getApplicationContext(),"finalizas Listagem listas",Toast.LENGTH_SHORT).show();


        }



    }

       @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            SQLiteCursor cursor = (SQLiteCursor) AdapterLista.getItem(position);
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
           String email = cursor.getString(cursor.getColumnIndex("email"));
          // startActivity(new Intent(getApplicationContext(),Inten).));

            Toast.makeText(getApplicationContext(),"/n selecionou o nome  :"+nome+ "/n Selecionou o email "+email,Toast.LENGTH_SHORT).show();



        }







    }










