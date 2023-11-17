package com.danilo.calculadoradeimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danilo.calculadoradeimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Variáves de Mensagem dinâmica e do botão calcular
        val bt_calcular = binding.btnCalcular
        val bt_limpar = binding.btnLimpar
        val mensagem = binding.mensagem

        //Setando um evento de Clique
        //Dentro do evento tem as variáveis declarada do editText
        //Tem uma condicional caso não esteja preenchido peso e altura
        bt_calcular.setOnClickListener {
            val editPeso = binding.editPeso.text.toString()
            val editAltura = binding.editAltura.text.toString()

            if (editPeso.isEmpty()) {
                mensagem.setText("Informe o seu peso")
            }else if (editAltura.isEmpty()){
                mensagem.setText("Informe a sua altura")
            }else {
                CalculoDeIMC()
            }
        }

        //Botão de Limpar
        //Pegar os textos que já foram setados
        //E passar como textos limpos para limpar e resetar os edit e textview
        bt_limpar.setOnClickListener {
            val limparEditPeso = binding.editPeso
            val limparEditAltura = binding.editAltura
            val limparMensagem = binding.mensagem

            limparEditPeso.setText("")
            limparEditAltura.setText("")
            limparMensagem.setText("")
        }

    }

    //Função calcular o IMC
    private fun CalculoDeIMC(){

        // Variável para pegar altura e peso
        val pesoID = binding.editPeso
        val alturaID = binding.editAltura

        // Variável para converter peso em inteiro e altura em float
        // Variável do resultado
        // Variável da conta do IMC
        val peso = Integer.parseInt(pesoID.text.toString())
        val altura = java.lang.Float.parseFloat(alturaID.text.toString())
        val resultado = binding.mensagem
        val imc = peso / (altura * altura)

        //Controle de fluxo When
        val Mensagem = when{
            imc <=18.5 -> "Peso Baixo"
            imc <=24.9 -> "Peso Normal"
            imc <=29.9 -> "Sobrepeso"
            imc <=34.9 -> "Obesidade (Grau 1)"
            imc <=39.9 -> "Obesidade (Grau 2)"
            else -> "Obesidade Mórbida (Grau 3)"
        }

        // Retornando o imc em String
        // Resultado retorna a Conta já resolvida junto com a resposta do controle
        imc.toString()
        resultado.setText("IMC $imc \n $Mensagem")

    }
}