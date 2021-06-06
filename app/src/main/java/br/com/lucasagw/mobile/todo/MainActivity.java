package br.com.lucasagw.mobile.todo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.widget.AdapterView.*;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private FloatingActionButton floatingButton;
    public static List<Tarefa> bd = new ArrayList<>();
    public TarefaAdapter tarefaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.app_name));

        String[] array = getResources().getStringArray(R.array.situacao_array); //n ta sendo usado no momento

        getResources().getQuantityString(R.plurals.numeroDeTarefa, 2); //controle de plural
        //view
        listView = findViewById(R.id.main_list_lista);
        floatingButton = findViewById(R.id.id_main_button_add);

        bd.addAll(Arrays.asList(new Tarefa("DEVER DE CASA", "DESCRICAO", 1),
                new Tarefa("ASSISTIR AULA", "DESCRICAO", 1),
                new Tarefa("TOMAR BANHO", "DESCRICAO", 2),
                new Tarefa("LER UM LIVRO", "DESCRICAO", 2)));

//        floatingButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, FormActivity.class);
//                //startActivity(intent);
//                startActivityForResult(intent, 999);
//            }
//        });

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {  //clique curto
                Toast.makeText(MainActivity.this, "OnItemClickListener", Toast.LENGTH_SHORT).show();
                Tarefa tarefa = (Tarefa) parent.getItemAtPosition(position);
                Intent formIntent = new Intent(MainActivity.this, FormActivity.class);
                formIntent.putExtra("TAREFA", tarefa);
                startActivity(formIntent);

            }
        });


        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) { //clique longo, qd clica e segura
                Toast.makeText(MainActivity.this, " onItemLongClick", Toast.LENGTH_SHORT).show();
                Tarefa tarefa = (Tarefa) parent.getItemAtPosition(position);
                bd.remove(tarefa);
                tarefaAdapter.remove(tarefa);

                //true - consome o evento,
                //false - repassa o evento
                return true;

            }
        });

        registerForContextMenu(listView); //para a listView entender que tem um contexMenu

    }

    @Override
    protected void onResume() {
        super.onResume();
        //lista de tarefa
        //  List<Tarefa> tarefas = new ArrayList<>(bd);
        tarefaAdapter = new TarefaAdapter(this, bd);
        listView.setAdapter(tarefaAdapter);

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //indicado para uso de uma activity implicita
//        super.onActivityResult(requestCode, resultCode, data);                               //mas funciona nos dois casos.
//        if (requestCode == 999) {
//            if (resultCode == 200) {
//                String nome = data.getStringExtra("NOME_DA_TAREFA");
//                Tarefa tarefa = new Tarefa();
//                tarefa.setNome(nome);
//                bd.add(tarefa);
//
//            }
//        }
//    }

    public void formulario(View view) {
        Intent formIntent = new Intent(this, FormActivity.class);
        startActivity(formIntent);
        Toast.makeText(this, "Vai para o form", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { //cria o menu
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) { //processa  a interação, pega o item clicado
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();//identifica o item clicado na tela
        switch (item.getItemId()) { //item q será selecionado
            case R.id.main_cmenu_excluir:
                Toast.makeText(this, tarefaAdapter.getItem(info.position).getTitulo(), Toast.LENGTH_SHORT).show();
                return true;

            case R.id.main_cmenu_enviar:
                return true;

        }
        return super.onContextItemSelected(item);
    }
}