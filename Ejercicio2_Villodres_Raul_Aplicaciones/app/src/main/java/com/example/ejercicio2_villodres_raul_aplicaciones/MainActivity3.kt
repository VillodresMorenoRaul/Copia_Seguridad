package com.example.ejercicio2_villodres_raul_aplicaciones

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ejercicio2_villodres_raul_aplicaciones.databinding.ActivityMain3Binding
import java.lang.String
import java.util.*


class MainActivity3 : AppCompatActivity() {


    private lateinit var binding: ActivityMain3Binding

    //Comenzamos declarando las variables, en kotlin estas se ponen automáticamente del tipo adecuado con poner "var", aunque también es válido indicarlo (abajo un ejemplo)
    var contador = 0
    //var Ejemplo: Long = 40
    var numerosPrimos = ArrayList<Int?>()
    var esPrimo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.primosEntreInicioYFin.setOnClickListener(View.OnClickListener {

            //Este try catch sirve para comprobar si los valores introducidos son válidos, si no lo son, devuelve el Toast correspondiente.
            try {
                var inicio: Int = Integer.valueOf(String.valueOf(binding.numeroInicio.text))
                var fin: Int = Integer.valueOf(String.valueOf(binding.numeroFin.text))

                binding.SerieNumeros.setText("") //Esta linea borra el texto del campo para que no se junten los resultados de pulsar varias veces el botón.

                inicio++; //Se suma un número para que solo se cuenten como números primos los que hay entre el de inicio y fin, sin contar extremos.

                //Este condicional invierte inicio y fin si el número de fin es mayor que el de inicio. Se podría simplemente indicar al final, pero veo más interesante hacerlo de esta manera.
                if(inicio > fin){
                    Toast.makeText(this, "El número de inicio es mayor que el de el final, el orden se invirtió al calcular los primos", Toast.LENGTH_SHORT).show()
                    inicio = Integer.valueOf(String.valueOf(binding.numeroFin.text))
                    fin = Integer.valueOf(String.valueOf(binding.numeroInicio.text))
                }

                //Se ha usado while en vez de for porque no he dado con la clave de implementarlo en kotlin cómo quería
                while (inicio < fin) {

                    esPrimo = true
                    var i = 2

                    while (i <= Math.sqrt(inicio.toDouble()) && esPrimo) {
                        if (inicio % i == 0) {
                            esPrimo = false
                        }

                        i++
                    }
                    if (esPrimo) {
                        numerosPrimos.add(inicio)
                        contador++
                    }

                    inicio++
                }


                val numerosPrimosAString = TextUtils.join(", ", numerosPrimos) //Este método convierte todos los valores del arrauList en un String separado por el delimitador que pongamos (en mi caso ",")
                binding.SerieNumeros.setText(numerosPrimosAString.toString()) //Aquí ajustamos el texto al String que creamos arriba
                numerosPrimos.removeAll(numerosPrimos) //Esta linea borra los elementos del array para que no se amontonen resultados previos y actuales al pulsar el botón

            } catch (e: Exception){
                Toast.makeText(this, "Uno o ambos números no son válidos", Toast.LENGTH_SHORT).show()
            }

        })
    }
}