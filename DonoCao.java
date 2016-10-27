package com.example.helio.bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helio.bancodedados.Dao.DonoDoCao;


public class DonoCao extends AppCompatActivity {


    private EditText txtNome;
    private TextView txtEmail;
    private TextView txtTelefone;
    private TextView txtSenha;
    private Button OK;
    private Button Salvar;
    TextView textView;


    Cursor cursor;

    SimpleCursorAdapter AdapterLista;
    //BDcore bd ;
    SQLiteDatabase db;
    ListView listViewListar;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dono_cao);


        criartabela();
//        deletar();
        txtNome = (EditText) findViewById(R.id.editNome);
        txtTelefone = (TextView) findViewById(R.id.editTelefone);
        txtEmail = (TextView) findViewById(R.id.editEmail);
        txtSenha =  (TextView)findViewById(R.id.editTextsenha);
        Button DelBtn =(Button)findViewById(R.id.butdeletando);
        Button salvarBtn = (Button) findViewById(R.id.butok);




        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonoDoCao donoDoCao = new DonoDoCao();
                donoDoCao.setNome(txtNome.getText().toString());
                donoDoCao.setTelefone(txtTelefone.getText().toString());
                donoDoCao.setEmail(txtEmail.getText().toString());
                donoDoCao.setSenha(txtSenha.getText().toString());

                SalvarContato(donoDoCao);

            }
        });

      DelBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             // DonoDoCao donoDoCao = new DonoDoCao();
              String nome = "nome";
              String where = "nome =?"+nome;
              //String _id = String.valueOf(donoDoCao.getNome());
             String[] whereArgs = new String[]{nome};
              //int count = deletar(where ,whereArgs);
              DonoDoCao donoDoCao = new DonoDoCao();


               db = openOrCreateDatabase("DonoCao.db",Context.MODE_PRIVATE,null);
               db.delete("DonoCao.db", "nome = " + donoDoCao.findViewById(R.id.editNome),null );
              Toast.makeText(getApplicationContext(),"Deleção Realizada com successo  !!:",Toast.LENGTH_SHORT).show();

              db.close();
              Toast.makeText(getApplicationContext(),"Fechando o Banco  :",Toast.LENGTH_SHORT).show();
          }
      });
    }

    public void MessageAlerta(String TituloAlerta, String MesagenAlerta) {

        AlertDialog.Builder Mensagen = new AlertDialog.Builder(DonoCao.this);
        Mensagen.setMessage(MesagenAlerta);
        Mensagen.setTitle(TituloAlerta);
        Mensagen.setNeutralButton("OK", null);
        Mensagen.show();
    }
    public void criartabela(){

        SQLiteDatabase db = null;

        try {
            db = openOrCreateDatabase("DonoCao.db",Context.MODE_PRIVATE,null);

            StringBuilder sql = new StringBuilder();

            sql.append("CREATE TABLE IF NOT EXISTS DonoCao(");
            sql.append("_id integer primary key autoincrement,");
            sql.append("nome varchar(120),");
            sql.append("telefone varchar(11),");
            sql.append("email varchar(50),");
            sql.append("senha varchar(10))");

            db.execSQL(sql.toString());
           // Toast.makeText(getApplicationContext(),"Tabela criada com exito",Toast.LENGTH_SHORT).show();
        }catch (Exception ex){

            Toast.makeText(getApplicationContext(),"ERROR na criacâo",Toast.LENGTH_SHORT).show();

        }finally {
            db.close();
            Toast.makeText(getApplication(),"fechando o Banco",Toast.LENGTH_SHORT).show();
        }
    }

    public void SalvarContato(DonoDoCao donoDoCao) {

       // SQLiteDatabase bd = null;

        try {

            db = openOrCreateDatabase("DonoCao.db", Context.MODE_PRIVATE, null);
            ContentValues contentValues = new ContentValues();
            contentValues.put("nome", donoDoCao.getNome());
            contentValues.put("telefone", donoDoCao.getTelefone());
            contentValues.put("email", donoDoCao.getEmail());
            contentValues.put("senha",donoDoCao.getSenha());

            db.insert("DonoCao", null, contentValues);

            txtNome.setText("");
            txtEmail.setText("");
            txtTelefone.setText("");
            txtSenha.setText("");

        } catch (Exception e) {

            e.getMessage();
            Toast.makeText(getApplicationContext(), "EROR AO INSERIR", Toast.LENGTH_SHORT).show();
        } finally {
            Toast.makeText(getApplicationContext(), "INSERIDO COM SUCESSO", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplication(), PrimeiraTela.class));
            db.close();
        }


    }
    public void deletar(String nome,String where,String[] whereArgs){
       // long id;

        where = "nome =?";
        String _id = String.valueOf(nome);
        whereArgs = new String[]{nome};
        //int count = deletar(where ,whereArgs);
        DonoDoCao donoDoCao = new DonoDoCao();
      //  db.delete();

        try {

           // String sql =" Delete * from DonoCao  where nome =?"+nome;
            db = openOrCreateDatabase("DonoCao.db",Context.MODE_PRIVATE,null);
            db.delete("DonoCao.db",  where, whereArgs);

            Toast.makeText(getApplicationContext(), "Sucesso UHUHUHU :", Toast.LENGTH_SHORT).show();

        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), "EROR AO Deletar", Toast.LENGTH_SHORT).show();

        }finally {

            Toast.makeText(getApplicationContext(), "Sucesso na REMOÇÂO :", Toast.LENGTH_SHORT).show();
            db.close();

        }
    }

}
