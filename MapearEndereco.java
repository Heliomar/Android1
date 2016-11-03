package com.example.helio.bancodedados;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Helio on 02/11/2016.
 */


public class MapearEndereco extends ListActivity implements View.OnClickListener{


    TextView textViewMapa;
    Cursor cursor;
    SimpleCursorAdapter Adapter;
    SQLiteDatabase db;
    EditText Endereco;


 public  void onCreate(Bundle icicle){
     super.onCreate(icicle);
     setContentView(R.layout.listarbairro);


     ChamaBairro();
     ListaBairro();
 }

    @Override
    public void onClick(View v) {


    }
    public void ListaBairro(){

        textViewMapa = (TextView)findViewById(R.id.BairroPoW);

      cursor.getColumnIndex("bairroPpousada");

        Adapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.listarbairro,cursor, null, null);
        textViewMapa.getText().toString();
        Adapter.getCursor();

    }

    public void ChamaBairro(){

        try {
            db = openOrCreateDatabase("Pousada.db", Context.MODE_PRIVATE,null);
            cursor = db.rawQuery("Select bairroPousada from Pousada",null);
            Toast.makeText(getApplicationContext(),"Sucesso ",Toast.LENGTH_SHORT).show();
        }catch (Exception ex){

            Toast.makeText(getApplicationContext(),"Falhou ",Toast.LENGTH_SHORT).show();

        }
        db.close();
    }
}
