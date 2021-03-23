package com.unitec.tlaloc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.TextView

import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MainActivity : AppCompatActivity() {
    var usuario=Mensaje()
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