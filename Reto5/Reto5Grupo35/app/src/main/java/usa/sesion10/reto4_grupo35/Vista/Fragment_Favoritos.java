package usa.sesion10.reto4_grupo35.Vista;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import usa.sesion10.reto4_grupo35.Modelo.Adaptador;
import usa.sesion10.reto4_grupo35.Modelo.BaseDatos.MotorBaseDatosSQLite;
import usa.sesion10.reto4_grupo35.Modelo.Entidad;
import usa.sesion10.reto4_grupo35.R;


public class Fragment_Favoritos extends Fragment {

    View v;

    String TAG = "Fragment_Favoritos";

    ListView listaFavoritos;
    Adaptador adaptador;

    int [] imagen = {R.drawable.chaqueta1, R.drawable.chaqueta2,R.drawable.chaqueta3,R.drawable.chaqueta1};

    // CONEXION A LA BASE DE DATOS: SQLite


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment__favoritos, container, false);
        //-----------------------------------------------------------------------------

        listaFavoritos = (ListView) v.findViewById(R.id.lista_favoritos);
        adaptador = new Adaptador(getTablaFavoritos(), getContext());

        listaFavoritos.setAdapter(adaptador);

        //-----------------------------------------------------------------------------
        return v;
    }

    private ArrayList<Entidad> getTablaFavoritos(){
        ArrayList<Entidad> listaFavoritos = new ArrayList<>();

        MotorBaseDatosSQLite conectar = new MotorBaseDatosSQLite(getContext(),"TiendaProductos", null, 1);

        SQLiteDatabase db_leer = conectar.getReadableDatabase();

        //conectar.onUpgrade(db_leer,1,2);

        Cursor cursor = db_leer.rawQuery("SELECT * FROM favoritos", null);


        while(cursor.moveToNext()){

            //listaFavoritos.add(new Entidad(imagen[cursor.getInt(0)], cursor.getString(1), cursor.getString(2)));
            //listaFavoritos.add(new Entidad(cursor.getString(0), imagen[cursor.getInt(1)], cursor.getString(2), cursor.getString(3)));
            listaFavoritos.add(new Entidad(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));

        }


        return listaFavoritos;
    }
}