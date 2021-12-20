package usa.sesion10.reto4_grupo35.Modelo.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MotorBaseDatosSQLite extends SQLiteOpenHelper {

    public MotorBaseDatosSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TABLA FAVORITOS---------------------------------------------------------------------------------------------
        //db.execSQL("DROP TABLE favoritos");
        db.execSQL("CREATE TABLE favoritos (id TEXT,img INT, titulo TEXT,descripcion TEXT)");
        //---- Registro
        //db.execSQL("INSERT INTO favoritos VALUES ('CHH01',0,'Chaqueta Hombre Casual','Precio: $127.432')");

        //TABLA PRODUCTOS---------------------------------------------------------------------------------------------
       // db.execSQL("DROP TABLE productos");
        db.execSQL("CREATE TABLE productos (id TEXT, img INT, titulo TEXT,descripcion TEXT)");
        //---- Registros
        db.execSQL("INSERT INTO productos VALUES ('CHH01',0,'Chaqueta Hombre Casual','Precio: $127.432')");
        db.execSQL("INSERT INTO productos VALUES ('CHH02',1,'Chaqueta Hombre University Club','Precio: $142.324')");
        db.execSQL("INSERT INTO productos VALUES ('CHH03',2,'Chaqueta Hombre Finland Roly','Precio: $99.999')");

        //TABLA SERVICIOS---------------------------------------------------------------------------------------------
       // db.execSQL("DROP TABLE servicios");
        db.execSQL("CREATE TABLE servicios (id TEXT,img INT,titulo TEXT,descripcion TEXT)");
        //---- Registros
        db.execSQL("INSERT INTO servicios VALUES ('SR01',0,'24/7','Ven y compra cuando quieras!')");
        db.execSQL("INSERT INTO servicios VALUES ('SR02',1,'Domicilios','Envíos a todo el pais')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE favoritos");
        db.execSQL("DROP TABLE productos");
        db.execSQL("DROP TABLE servicios");
        onCreate(db);
       

    }
}
