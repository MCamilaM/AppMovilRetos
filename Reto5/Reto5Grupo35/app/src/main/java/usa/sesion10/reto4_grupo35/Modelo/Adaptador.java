package usa.sesion10.reto4_grupo35.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import usa.sesion10.reto4_grupo35.Modelo.BaseDatos.MotorBaseDatosSQLite;
import usa.sesion10.reto4_grupo35.R;


public class Adaptador extends BaseAdapter {

    ArrayList<Entidad> lista_items;
    Context context;

    // CONEXION A LA BASE DE DATOS: SQLite
    MotorBaseDatosSQLite conectar;



    public Adaptador(ArrayList<Entidad> lista_items, Context context) {
        this.lista_items = lista_items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista_items.size(); // Devuelve cuantos elelmentos hay en la lista
    }

    @Override
    public Object getItem(int posicion) {
        return lista_items.get(posicion); // devuelve la posicion del item
    }

    @Override
    public long getItemId(int posicion) {
        return 0; // No lo vamos a trabajar
    }

    /*
    Este es el metodo mas importante, aqui vamos a asignar el item y lo elementos y valores a
    cada item.
     */
    @Override
    public View getView(int posicion, View v, ViewGroup viewGroup) {

        Entidad datosItem = (Entidad) getItem(posicion);

        v = LayoutInflater.from(context).inflate(R.layout.item, null);
        //-------------------------------------------------------------------

        TextView id = (TextView)v.findViewById(R.id.id);
        ImageView imagen = (ImageView)v.findViewById(R.id.imagen1_item);
        TextView titulo = (TextView)v.findViewById(R.id.titulo_item);
        TextView descripcion = (TextView)v.findViewById(R.id.descripcion_item);
        CheckBox favoritos = (CheckBox) v.findViewById(R.id.favoritos_item);


        /*
        Pongo los datos de cada item desde la clase Entidad dentro de cada elemento xml
         */


        id.setText(datosItem.getId());
        // ---Cargue de imagen con url----------------------
        Picasso.get ()
                .load (datosItem.getImagen())
                .error (R.mipmap.ic_launcher_round)
                .into(imagen);
        //--------------------------------------------------
        //imagen.setImageResource(datosItem.getImagen());
        titulo.setText(datosItem.getTitulo());
        descripcion.setText(datosItem.getDescripcion());



        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Guardado en favoritos", Toast.LENGTH_LONG).show();
                if(favoritos.isChecked()){

                    conectar = new MotorBaseDatosSQLite(context,"TiendaProductos", null, 1);
                    SQLiteDatabase db_escribir = conectar.getWritableDatabase();

                    Toast.makeText(context, "Guardado en favoritos", Toast.LENGTH_LONG).show();
                    db_escribir.execSQL("DELETE FROM favoritos WHERE id= '"+ datosItem.getId() + "'");
                    db_escribir.execSQL("INSERT INTO favoritos VALUES ('" + datosItem.getId() + "','" + datosItem.getImagen() + "','" + datosItem.getTitulo() + "','" + datosItem.getDescripcion()+"')");
                }
                //favoritos.setChecked(false);

            }
        });

        //-------------------------------------------------------------------
        return v;
    }

}
