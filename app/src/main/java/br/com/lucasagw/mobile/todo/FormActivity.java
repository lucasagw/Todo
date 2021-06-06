package br.com.lucasagw.mobile.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class FormActivity extends AppCompatActivity {
    private TextInputEditText textInputEditTitulo;
    private TextInputEditText textInputEditDescricao;
    private TextInputEditText textInputEditPrioridade;
    private Button buttonSalvar;
    private Tarefa tarefa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);


        textInputEditTitulo = findViewById(R.id.form_edit_titulo);
        textInputEditDescricao = findViewById(R.id.form_edit_descricao);
        textInputEditPrioridade = findViewById(R.id.form_edit_prioridade);

        buttonSalvar = findViewById(R.id.form_button_salvar);


//        buttonSalvar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //Não fazer. Somente se a intenção for realmente navegar.
//                //Intent intent = new Intent(FormActivity.this, MainActivity.class);
//                //startActivity(intent);
//
//                //Activity anterior não espera um retorno
////                MainActivity.bd.add(editText.getText().toString());
////                finish();
//
//                //Activity anterior espera um retorno
//                // Intent data = new Intent(); funciona dos dois jeitos. criando uma nova intent ou reaproveitando intent.
//                getIntent().putExtra("NOME_DA_TAREFA", editText.getText().toString());
//                setResult(200, getIntent());
//                finish();
//
//            }
//        });

        if (getIntent().hasExtra("TAREFA")) {
            setTitle("ALTERAR TAREFA");
            tarefa = (Tarefa) getIntent().getSerializableExtra("TAREFA");
            textInputEditTitulo.setText(tarefa.getTitulo()); //o get retorna as informações salvas anteriormente, e eu decido se vou atualizar ou não.
            textInputEditDescricao.setText(tarefa.getDescricao());
            textInputEditPrioridade.setText(tarefa.getPrioridade() + "");

        } else {
            setTitle("CRIAR TAREFA");

        }

    }

    public void salvar(View view) {
        salvarTarefa();

    }

    private void salvarTarefa() {
        if (tarefa == null) {
            tarefa = new Tarefa(); //quando for criação, eu instancio um novo objeto de tarefa
            MainActivity.bd.add(tarefa);
        } else {//quando for alteração, eu reutilizo aquele instância da tarefa clicado (instanciado anteriormente, que tem seu espaço já alocado).
            //apagar e inserir novamente, nao fazer, pq gasta processamento, -> MainActivity.bd.remove(tarefa); //pra conseguir achar a tarefa certa pra remover, teve que sobrescrever equals/hashcode.
            int index = MainActivity.bd.indexOf(tarefa); //recupera posição da instância clicada
            tarefa = MainActivity.bd.get(index); //recupera a propria instância clicada, possibilitando a atualização dos campos que já estão alocados.
        }
        tarefa.setTitulo(textInputEditTitulo.getText().toString());
        tarefa.setDescricao(textInputEditDescricao.getText().toString());
        tarefa.setPrioridade(Integer.parseInt(textInputEditPrioridade.getText().toString()));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //cria o menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.form_menu, menu);

        // menu.add("OK"); outra forma de inserir menu
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //processa a interação
        Toast.makeText(this, "Menu" + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.form_menu_salvar:
                salvarTarefa();
                // salvar(item.getActionView());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}