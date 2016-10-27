package com.example.helio.bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helio.bancodedados.Dao.DonoDoCao;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Helio on 27/09/2016.
 */
public class BDcore extends AppCompatActivity {

    SQLiteDatabase bd;
    SimpleCursorAdapter cursor;
    ListView listViewContatos;



    TextView textNome = (TextView) findViewById(R.id.editNome);
    TextView textEmail = (TextView) findViewById(R.id.editEmail);
    TextView textTelefone= (TextView) findViewById(R.id.editTelefone);


    public void inserir(DonoDoCao donoDoCao) {
        //SQLiteDatabase bd = null;

        try {

            ContentValues valor = new ContentValues();

            valor.put("txtNome", donoDoCao.getNome());
            valor.put("txtTelefone", donoDoCao.getTelefone());
            valor.put("txtEmail", donoDoCao.getEmail());
            valor.put("txtSenha", donoDoCao.getSenha());

            bd.insert("DonoCao",null,valor);



            textNome.setText("");
            textTelefone.setText("");
            textEmail.setText("");
            Toast.makeText(getApplicationContext(),"insercação com exito",Toast.LENGTH_SHORT).show();

        }catch (Exception e){

            e.getMessage();
        }

    }

    public void deletarDono(DonoDoCao donoDoCao) {



        try{
            bd = openOrCreateDatabase("DonoCao.db",Context.MODE_PRIVATE,null);
            bd.delete("DonoCao.db","id =?"+donoDoCao.getId(), null);
            bd.close();
           //cursor = bd.execSQL("Delete FROM DonoCao","id =?",)'");
            //cursor = bd.delete("donocao", "id = ?",+id String.valueOf(id));//);//: null;; //delete//("DonoCao","id ="+id ,null);



        }catch (Exception ex){


        }finally {

            Toast.makeText(getApplicationContext(),"Fechando banco de Dados",Toast.LENGTH_SHORT).show();
        }

    }

    public void atualizarDono(DonoDoCao donoDoCao) {

        ContentValues valor = new ContentValues();
        valor.put("nome",donoDoCao.getNome());
        bd.update("DonoCao",valor,"nome=?",new String[]{""+donoDoCao.getNome()});


    }

    public void list_dono() {

            }




    public void buscar(SQLiteDatabase bd){
        try {


            bd.rawQuery("select * from DonoCao", null);


        }catch (Exception e){

            e.getMessage();
        }

    }
}
