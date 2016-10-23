package br.usjt.arqdesis.clientep3.Model;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.Hashtable;
import java.util.Locale;

import br.usjt.arqdesis.clientep3.R;

/**
 * Created by asbonato on 9/19/16.
 */
public class ClienteAdapter extends BaseAdapter implements SectionIndexer
{
    Activity context;
    Cliente[] clientes;
    Object[] sectionHeaders;
    Hashtable<Integer, Integer> positionForSectionMap;
    Hashtable<Integer, Integer> sectionForPositionMap;

    public ClienteAdapter(Activity context, Cliente[] clientes){
        this.context = context;
        this.clientes = clientes;
        sectionHeaders = SectionIndexBuilder.BuildSectionHeaders(clientes);
        positionForSectionMap = SectionIndexBuilder.BuildPositionForSectionMap(clientes);
        sectionForPositionMap = SectionIndexBuilder.BuildSectionForPositionMap(clientes);

    }
    @Override
    public int getCount() {
        return clientes.length;
    }

    @Override
    public Object getItem(int position) {
        if(position >= 0 && position < clientes.length)
            return clientes[position];
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //o list view recicla os layouts para melhor performance
        //o layout reciclado vem no parametro convert view
        View view = convertView;
        //se nao recebeu um layout para reutilizar deve inflar um
        if(view == null) {
            //um inflater transforma um layout em uma view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_cliente, parent, false);

            ImageView letraCliente = (ImageView)view.findViewById(R.id.foto_cliente);
            TextView nomeCliente = (TextView)view.findViewById(R.id.nome_cliente);
            TextView detalheCliente = (TextView)view.findViewById(R.id.detalhe_cliente);
            //faz cache dos widgets instanciados na tag da view para reusar quando houver reciclagem
            view.setTag(new ViewHolder(letraCliente, nomeCliente, detalheCliente));
        }
        //usa os widgets cacheados na view reciclada
        ViewHolder holder = (ViewHolder)view.getTag();
        //carrega os novos valores
        Drawable drawable = Util.getDrawable(context, clientes[position].getFoto());
        holder.getFotoCliente().setImageDrawable(drawable);
        Locale locale = new Locale("pt", "BR");
        holder.getNomeCliente().setText(clientes[position].getNome());
        holder.getDetalheCliente().setText(String.format("%s - %s", clientes[position].getFone(),
                clientes[position].getEmail()));

        return view;
    }
//metodos da interface SectionIndexer


    @Override
    public Object[] getSections() {
        return sectionHeaders;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return positionForSectionMap.get(sectionIndex).intValue();
    }

    @Override
    public int getSectionForPosition(int position) {
        return sectionForPositionMap.get(position).intValue();
    }
}
