package com.longasjulian.cambiomoneda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Array

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerIN: Spinner = findViewById(R.id.MonedaIn_SP)
        ArrayAdapter.createFromResource(
            this,
            R.array.Moneda,
            R.layout.spinner_item_prueba
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_item_prueba)
            spinnerIN.adapter = adapter
        }
        val spinnerOUT: Spinner = findViewById(R.id.MonedaOut_SP)
        ArrayAdapter.createFromResource(
            this,
            R.array.Moneda,
            R.layout.spinner_item_prueba
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_item_prueba)
            spinnerOUT.adapter = adapter
        }

        Convertir_BT.setOnClickListener{

            //Lectura de valores de los spinners
            var MonedaIn    = MonedaIn_SP.selectedItem.toString()
            var MonedaOut   = MonedaOut_SP.selectedItem.toString()

            //Constantes de Comparacion de Strings
            val PesoColombiano       = "Peso Colombiano"
            val DolarEstadounidense  = "Dólar Estadounidense"
            val PesoMexicano         = "Peso Mexicano"

            //Constantes de Cambio
            var constCambio = 1.0

            //Lectura del valor que se desea convertir
            var valorMonedaIn= Moneda_in_TV.text.toString().toDouble()

            var valorMonedaOut= 1.0

            if (MonedaIn.equals(MonedaOut)) {
                Resultado_TV.text   = "Ingrese diferentes tipos de moneda"
                Moneda_out_TV.text  = ""
            }
            else{

                if(MonedaIn.equals(PesoColombiano) && MonedaOut.equals(DolarEstadounidense)) //CO to US
                    constCambio     = 0.00027
                else if(MonedaIn.equals(PesoColombiano) && MonedaOut.equals(PesoMexicano))  //CO to MX
                    constCambio     = 0.0059
                else if(MonedaIn.equals(DolarEstadounidense) && MonedaOut.equals(PesoColombiano)) //US to CO
                    constCambio     = 3746.50
                else if(MonedaIn.equals(DolarEstadounidense) && MonedaOut.equals(PesoMexicano))  //US to MX
                    constCambio     = 22.29
                else if(MonedaIn.equals(PesoMexicano) && MonedaOut.equals(PesoColombiano)) //MX to CO
                    constCambio     = 167.99
                else if(MonedaIn.equals(PesoMexicano) && MonedaOut.equals(DolarEstadounidense))  //MX to US
                    constCambio     = 0.045
                valorMonedaOut  = valorMonedaIn * constCambio
                Moneda_out_TV.text  = valorMonedaOut.toString()
                Resultado_TV.text   = "1 $MonedaIn equivale a $constCambio $MonedaOut"
            }


        }








    }
}