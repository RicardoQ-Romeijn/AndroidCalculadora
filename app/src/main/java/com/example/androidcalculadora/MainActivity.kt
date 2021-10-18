package com.example.androidcalculadora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private var num1: Double? = null
    private var numRes: Double? = null
    private var operacion: Int = 0
    private var modo: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonResultado.setOnClickListener { resultado() }
        buttonSuma.setOnClickListener { suma() }
        buttonResta.setOnClickListener { resta() }
        buttonMultiplicar.setOnClickListener { multi() }
        buttonDividir.setOnClickListener { dividir() }

        buttonPunto.setOnClickListener { buttonPunto() }
        buttonBorrar.setOnClickListener { buttonClear() }

        buttonN1.setOnClickListener { buttonChar("1") }
        buttonN2.setOnClickListener { buttonChar("2") }
        buttonN3.setOnClickListener { buttonChar("3") }
        buttonN4.setOnClickListener { buttonChar("4") }
        buttonN5.setOnClickListener { buttonChar("5") }
        buttonN6.setOnClickListener { buttonChar("6") }
        buttonN7.setOnClickListener { buttonChar("7") }
        buttonN8.setOnClickListener { buttonChar("8") }
        buttonN9.setOnClickListener { buttonChar("9") }
        buttonN0.setOnClickListener { buttonChar("0") }

        buttonA.setOnClickListener { buttonChar("A") }
        buttonB.setOnClickListener { buttonChar("B") }
        buttonC.setOnClickListener { buttonChar("C") }
        buttonD.setOnClickListener { buttonChar("D") }
        buttonE.setOnClickListener { buttonChar("E") }
        buttonF.setOnClickListener { buttonChar("F") }

        buttonA.setEnabled(false)
        buttonB.setEnabled(false)
        buttonC.setEnabled(false)
        buttonD.setEnabled(false)
        buttonE.setEnabled(false)
        buttonF.setEnabled(false)

        buttonHexa.setOnClickListener {
            modo = 2
            numRes = null
            num1 = null
            textViewResultado.setText("0")
            buttonA.setEnabled(true)
            buttonB.setEnabled(true)
            buttonC.setEnabled(true)
            buttonD.setEnabled(true)
            buttonE.setEnabled(true)
            buttonF.setEnabled(true)
            buttonN0.setEnabled(true)
            buttonN1.setEnabled(true)
            buttonN2.setEnabled(true)
            buttonN3.setEnabled(true)
            buttonN4.setEnabled(true)
            buttonN5.setEnabled(true)
            buttonN6.setEnabled(true)
            buttonN7.setEnabled(true)
            buttonN8.setEnabled(true)
            buttonN9.setEnabled(true)
            buttonPunto.setEnabled(false)
        }

        buttonBinario.setOnClickListener {
            modo = 3
            numRes = null
            num1 = null
            textViewResultado.setText("0")
            buttonA.setEnabled(false)
            buttonB.setEnabled(false)
            buttonC.setEnabled(false)
            buttonD.setEnabled(false)
            buttonE.setEnabled(false)
            buttonF.setEnabled(false)
            buttonN0.setEnabled(true)
            buttonN1.setEnabled(true)
            buttonN2.setEnabled(false)
            buttonN3.setEnabled(false)
            buttonN4.setEnabled(false)
            buttonN5.setEnabled(false)
            buttonN6.setEnabled(false)
            buttonN7.setEnabled(false)
            buttonN8.setEnabled(false)
            buttonN9.setEnabled(false)
            buttonPunto.setEnabled(false)
        }

        buttonDecimal.setOnClickListener {
            modo = 1
            numRes = null
            num1 = null
            textViewResultado.setText("0")
            buttonA.setEnabled(false)
            buttonB.setEnabled(false)
            buttonC.setEnabled(false)
            buttonD.setEnabled(false)
            buttonE.setEnabled(false)
            buttonF.setEnabled(false)
            buttonN0.setEnabled(true)
            buttonN1.setEnabled(true)
            buttonN2.setEnabled(true)
            buttonN3.setEnabled(true)
            buttonN4.setEnabled(true)
            buttonN5.setEnabled(true)
            buttonN6.setEnabled(true)
            buttonN7.setEnabled(true)
            buttonN8.setEnabled(true)
            buttonN9.setEnabled(true)
            buttonPunto.setEnabled(true)
        }

    }

    fun toBinary(decimal: Int) {
        val decimal = decimal
        var binar = "0"

        binar = decimal.toString(2)
        textViewResultado.setText(binar)
    }

    fun toDecimalFromBinary(binario: String) {
        val binario = binario
        var decimal = 0

        decimal = binario.toInt(2)
        textViewResultado.setText(decimal.toString())
    }

    fun toHexa(decimal: Int) {
        val decimal = decimal
        var hexa = "0"

        hexa = decimal.toString(16)
        textViewResultado.setText(hexa)
    }

    fun toDecimalFromHexa(hexa: String) {
        val hexa = hexa
        var decimal = 0

        decimal = hexa.toInt(16)
        textViewResultado.setText(decimal.toString())
    }

    private fun resultado() {
        try {
            if (operacion != 0) {

                if (modo == 3) {
                    toDecimalFromBinary( textViewResultado.text.toString() )
                } else if (modo == 2) {
                    toDecimalFromHexa( textViewResultado.text.toString() )
                }

                num1 = textViewResultado.text.toString().toDouble()

                when (operacion) {
                    1 -> numRes = num1?.let { numRes?.plus(it) }
                    2 -> numRes = num1?.let { numRes?.minus(it) }
                    3 -> numRes = num1?.let { numRes?.times(it) }
                    4 ->
                        if (num1 != 0.0) {
                            numRes = num1?.let { numRes?.div(it) }
                        }
                    else -> operacion = 0
                }

                if (operacion != 0) {
                    textViewResultado.setText(numRes.toString())

                    if (numRes.toString().contains(".0")) {
                        numRes?.toInt()
                    }

                    if (textViewResultado.text.toString() != "0" || textViewResultado.text.toString() != "0." || textViewResultado.text.toString() != "0.0" ) {
                        textViewResultado.setText(numRes.toString())
                    } else {
                        textViewResultado.setText("0")
                    }

                    if (modo == 3) {
                        toBinary(numRes?.toInt() ?: 0)
                    } else if (modo == 2) {
                        toHexa(numRes?.toInt() ?: 0)
                    }
                }

                operacion = 0
                num1 = null
                numRes = null
            }
        } catch (e: NumberFormatException) {
            println(e)
        }

    }

    private fun suma() {
        try {
            operacion = 1

            if (modo == 3) {
                toDecimalFromBinary( textViewResultado.text.toString() )
            } else if (modo == 2) {
                toDecimalFromHexa( textViewResultado.text.toString() )
            }

            if (numRes == null) {

                numRes = textViewResultado.text.toString().toDouble()
                textViewResultado.setText("0")

            } else {

                num1 = textViewResultado.text.toString().toDouble()
                numRes = numRes!! + num1!!
                if (numRes.toString().contains(".0")) {
                    numRes!!.toInt()
                }
                textViewResultado.setText(numRes.toString())

                if (modo == 3) {
                    toBinary(numRes?.toInt() ?: 0)
                } else if (modo == 2) {
                    toHexa(numRes?.toInt() ?: 0)
                }

            }
        } catch (e: NumberFormatException) {
            println(e)
        }
    }

    private fun resta() {
        try {
            operacion = 2

            if (modo == 3) {
                toDecimalFromBinary( textViewResultado.text.toString() )
            } else if (modo == 2) {
                toDecimalFromHexa( textViewResultado.text.toString() )
            }

            if (numRes == null) {

                numRes = textViewResultado.text.toString().toDouble()
                textViewResultado.setText("0")
            } else {
                num1 = textViewResultado.text.toString().toDouble()
                numRes = numRes!! - num1!!
                if (numRes.toString().contains(".0")) {
                    numRes!!.toInt()
                }
                textViewResultado.setText(numRes.toString())

                if (modo == 3) {
                    toBinary(numRes?.toInt() ?: 0)
                } else if (modo == 2) {
                    toHexa(numRes?.toInt() ?: 0)
                }
            }
        } catch (e: NumberFormatException) {
            println(e)
        }
    }

    private fun multi() {
        try {
            operacion = 3

            if (modo == 3) {
                toDecimalFromBinary( textViewResultado.text.toString() )
            } else if (modo == 2) {
                toDecimalFromHexa( textViewResultado.text.toString() )
            }

            if (numRes == null) {

                numRes = textViewResultado.text.toString().toDouble()
                textViewResultado.setText("0")
            } else {
                num1 = textViewResultado.text.toString().toDouble()
                numRes = numRes!! * num1!!
                if (numRes.toString().contains(".0")) {
                    numRes!!.toInt()
                }
                textViewResultado.setText(numRes.toString())

                if (modo == 3) {
                    toBinary(numRes?.toInt() ?: 0)
                } else if (modo == 2) {
                    toHexa(numRes?.toInt() ?: 0)
                }
            }
        } catch (e: NumberFormatException) {
            println(e)
        }
    }

    private fun dividir() {
        try {
            operacion = 4

            if (modo == 3) {
                toDecimalFromBinary( textViewResultado.text.toString() )
            } else if (modo == 2) {
                toDecimalFromHexa( textViewResultado.text.toString() )
            }

            if (numRes == null) {

                numRes = textViewResultado.text.toString().toDouble()
                textViewResultado.setText("0")
            } else {
                num1 = textViewResultado.text.toString().toDouble()
                if (num1 != 0.0) {
                    numRes = numRes!! / num1!!
                    textViewResultado.setText(numRes.toString())

                    if (modo == 3) {
                        toBinary(numRes?.toInt() ?: 0)
                    } else if (modo == 2) {
                        toHexa(numRes?.toInt() ?: 0)
                    }
                }
            }
        } catch (e: NumberFormatException) {
            println(e)
        }
    }

    private fun buttonPunto() {
        if (textViewResultado.text.toString() == "0") {
            textViewResultado.setText("0.")
        } else if (textViewResultado.text.toString() == numRes.toString()) {
            textViewResultado.setText("0.")
        } else {
            if (!textViewResultado.text.contains(".")) {
                textViewResultado.setText(textViewResultado.text.toString() + ".")
            } else if (textViewResultado.text.contains(".0")){
                numRes = textViewResultado.text.toString().toDouble()
                textViewResultado.setText(numRes!!.roundToInt().toString() + ".")
                numRes = null
                num1 = null
            } else {
                textViewResultado.setText(textViewResultado.text.toString())
            }
        }
    }

    private fun buttonClear() {
        num1 = null
        numRes = null
        textViewResultado.setText("0")
    }

    private fun buttonChar(s: String) {
        if (textViewResultado.text.toString() == "0") {
            textViewResultado.setText(s)
        } else if (textViewResultado.text.toString() == numRes.toString()) {
            textViewResultado.setText(s)
        } else {
            textViewResultado.setText(textViewResultado.text.toString() + s)
        }
    }


}