package com.unitec.tlaloc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MainActivity : AppCompatActivity() {
    var usuario=Mensaje()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menusito, menu)
        //Aqui acciones
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem)=when (item.itemId) {

        R.id.action_favorite -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...

            Toast.makeText(applicationContext,"Una opcion", Toast.LENGTH_LONG).show()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    var txt=    findViewById<TextView>(R.id.tutu)

   
        GlobalScope.launch(Dispatchers.IO){

           var txt = findViewById<TextView>(R.id.tutu)
           var retrofit = Retrofit.Builder()
                   .baseUrl("https://benesuela.herokuapp.com/")
                   .addConverterFactory(JacksonConverterFactory.create())
                   .build()

           //PASO 2: GENERAR UN OBJETO PARAHABILITAR TU SERVICIO DE RETROFIT USANDO EL OBJETO
           //DEL PUNTO ANTERIOR
           var servicioUsuario = retrofit.create(ServicioUsuario::class.java)


           //PASO 2
           var enviarUsuarios = servicioUsuario.buscarTodos()



           usuario = Mensaje()
           //SE ENVIA  AL BACK- END Y  EN ESTE MOMENTO SE OBTIENE LA RESPUESTA
           usuario = enviarUsuarios.execute().body()!!

        //Aqui viene la magia para compartir con el activity BusquedasActvity
        //nuestra variable usuarios de arriba


            Log.i("TTT","Usuarios encontrados ${usuario?.cuerpo}")
            launch(Dispatchers.Main) {
                txt.text=usuario?.cuerpo
            }
      //


    }


    }
}