package br.com.lucasagw.mobile.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class TarefaAdapter extends BaseAdapter {

    private List<Tarefa> tarefas;
    private Context contexto;

    public TarefaAdapter() {
    }

    public TarefaAdapter(Context context, List<Tarefa> tarefas) {
        this.contexto = context;
        this.tarefas = tarefas;

    }

    @Override
    public int getCount() {
        return tarefas.size(); //saber quantos itens tem na lista de tarefas
    }

    @Override
    public Tarefa getItem(int position) {
        return tarefas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(contexto).inflate(R.layout.tarefa_list_item_3, parent, false); //reutiliza view
        }

        Tarefa tarefa = getItem(position);

        TextView titulo = convertView.findViewById(R.id.list_tarefa3_titulo);
        titulo.setText(tarefa.getTitulo());
        TextView descricao = convertView.findViewById(R.id.list_tarefa3_descricao);
        descricao.setText(tarefa.getDescricao());
        TextView prioridade = convertView.findViewById(R.id.list_tarefa3_prioridade);
        prioridade.setText(tarefa.getPrioridade() + "");

        return convertView;
    }

    public void remove(Tarefa tarefa) { //remove da view
        tarefas.remove(tarefa);
        notifyDataSetChanged(); //notifica sobre mudança na view
    }

}

