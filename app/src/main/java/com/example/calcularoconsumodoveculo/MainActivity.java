package com.example.calcularoconsumodoveculo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText editKmPercurso;
    private TextInputEditText editKmLitro;
    private TextInputEditText editValorCombustivel;
    private TextInputEditText editQuantasPessoas;

    private Switch switchDividirCorrida;

    private TextView resultado1;
    private TextView resultado2;
    private TextView resultado3;
    private TextView resultado4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        AdView mAdView = findViewById(R.id. adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        editKmPercurso = findViewById(R.id.editKmPercurso);
        editKmLitro = findViewById(R.id.editKmLitro);
        editValorCombustivel = findViewById(R.id.editValorCombustivel);

        editQuantasPessoas = findViewById(R.id.editQuantasPessoas);
        editQuantasPessoas.setVisibility(View.GONE);

        switchDividirCorrida = findViewById(R.id.switchDividirCorrida);

        resultado1 = findViewById(R.id.resultado1);
        resultado2 = findViewById(R.id.resultado2);
        resultado3 = findViewById(R.id.resultado3);

        resultado4 = findViewById(R.id.resultado4);
        resultado4.setVisibility(View.GONE);

        //switchDividirCorrida.setChecked(false);



        switchDividir();
        limparTudo();
    }

    ArrayList<String> validarSwitch = new ArrayList<String>();

    // Aparece a opção de quantida de pessoas será dividia a corrida
    public void switchDividir() {

        switchDividirCorrida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (switchDividirCorrida.isChecked()) {

                    editQuantasPessoas.setVisibility(View.VISIBLE);
                    resultado4.setVisibility(View.VISIBLE);
                    validarSwitch.add("1");

                } else {

                    editQuantasPessoas.setVisibility(View.GONE);
                    resultado4.setVisibility(View.GONE);
                    validarSwitch.remove(0);
                }


                System.out.println(validarSwitch.size());

            }

        });

    }


   //* ***********Metodo 1*************


    public void Metodo1() {


        String kmPercurso = editKmPercurso.getText().toString();
        String kmLitro = editKmLitro.getText().toString();
        String valorCombustivel = editValorCombustivel.getText().toString();


        //Validar os campos digitados
        Boolean camposValidados = validarCampos(kmPercurso, kmLitro, valorCombustivel);
        if (camposValidados) {

            //Convertendo string para números
            double kmPercursoDouble = Double.parseDouble(kmPercurso);
            double kmLitroDouble = Double.parseDouble(kmLitro);
            double valorCombustivelDouble = Double.parseDouble(valorCombustivel);

            double calcularLitro = kmPercursoDouble / kmLitroDouble;
            double calcularValor = calcularLitro * valorCombustivelDouble;

            String va1 = String.format("%.2f",calcularLitro);
            String va2 = String.format("%.2f",calcularValor);

            if (kmPercursoDouble > 0 && kmLitroDouble > 0 && valorCombustivelDouble > 0) {
                resultado1.setVisibility(View.GONE);
                resultado2.setVisibility(View.VISIBLE);
                resultado3.setVisibility(View.VISIBLE);

                resultado2.setText("Você irá precisar de " + va1 + " litro de combustível.");
                resultado3.setText("E irá gastar aproximadamente R$ " + va2 + " reais de combustível.");


            } else {
                resultado1.setVisibility(View.VISIBLE);
                resultado2.setVisibility(View.GONE);
                resultado3.setVisibility(View.GONE);
                resultado4.setVisibility(View.GONE);
                resultado1.setText("Nenhum valor pode ser menor ou igual a 0!");

            }


        } else {
            resultado1.setVisibility(View.VISIBLE);
            resultado2.setVisibility(View.GONE);
            resultado3.setVisibility(View.GONE);

            resultado1.setText("Preencha todos os campos primeiro!");
        }


    }


    public Boolean validarCampos(String kmPercusroValidado, String kmPorLitroValidado, String valorCombustivelValidado) {

        Boolean camposValidados = true;
        if (kmPercusroValidado == null || kmPercusroValidado.equals("")) {
            camposValidados = false;

        } else if (kmPorLitroValidado == null || kmPorLitroValidado.equals("")) {
            camposValidados = false;

        } else if (valorCombustivelValidado == null || valorCombustivelValidado.equals("")) {
            camposValidados = false;
        }
        return camposValidados;
    }



    //*************************************************************************************************************


   //******************Metodo 2*****************


    public void metodo2() {


        String kmPercurso = editKmPercurso.getText().toString();
        String kmLitro = editKmLitro.getText().toString();
        String valorCombustivel = editValorCombustivel.getText().toString();
        String quantasPessoas = editQuantasPessoas.getText().toString();

        //Validar os campos digitados
        Boolean camposValidadosArry = validarCamposArray(kmPercurso, kmLitro, valorCombustivel, quantasPessoas);
        if (camposValidadosArry) {

            //Convertendo string para números
            double kmPercursoDouble = Double.parseDouble(kmPercurso);
            double kmLitroDouble = Double.parseDouble(kmLitro);
            double valorCombustivelDouble = Double.parseDouble(valorCombustivel);
            int quantidadePessoasInt = Integer.parseInt(quantasPessoas);

            double calcularLitro = kmPercursoDouble / kmLitroDouble;
            double calcularValor = calcularLitro * valorCombustivelDouble;
            double CalcularDividoPorPessoa = calcularValor/ quantidadePessoasInt;

            String va1 = String.format("%.2f",calcularLitro);
            String va2 = String.format("%.2f",calcularValor);
            String va3 = String.format("%.2f",CalcularDividoPorPessoa);


            if (kmPercursoDouble > 0 && kmLitroDouble > 0 && valorCombustivelDouble > 0 && quantidadePessoasInt > 1) {
                resultado1.setVisibility(View.GONE);
                resultado2.setVisibility(View.VISIBLE);
                resultado3.setVisibility(View.VISIBLE);
                resultado4.setVisibility(View.VISIBLE);

                resultado2.setText("Você irá precisar de " + va1 + " litro de combustível.");
                resultado3.setText("E irá gastar aproximadamente R$ " + va2 + " reais de combustível.");
                resultado4.setText(  "O valor dividido por " + quantidadePessoasInt + " pessoas vai fica aproximadamente R$ "+ va3  + " reais por pessoa.");


            } else {
                resultado1.setVisibility(View.VISIBLE);
                resultado2.setVisibility(View.GONE);
                resultado3.setVisibility(View.GONE);
                resultado4.setVisibility(View.GONE);
                resultado1.setText("Nenhum valor pode ser menor ou igual a 0, ou pessoas menor que 2!");

            }


        } else {
            resultado1.setVisibility(View.VISIBLE);
            resultado2.setVisibility(View.GONE);
            resultado3.setVisibility(View.GONE);
            resultado4.setVisibility(View.GONE);

            resultado1.setText("Preencha todos os campos primeiro!");
        }


    }



    public Boolean validarCamposArray(String kmPercusroValidado, String kmPorLitroValidado, String valorCombustivelValidado, String quantidadePessoas) {

        Boolean camposValidadosArry = true;
        if (kmPercusroValidado == null || kmPercusroValidado.equals("")) {
            camposValidadosArry = false;

        } else if (kmPorLitroValidado == null || kmPorLitroValidado.equals("")) {
            camposValidadosArry = false;

        } else if (valorCombustivelValidado == null || valorCombustivelValidado.equals("")) {
            camposValidadosArry = false;

        } else if (quantidadePessoas == null || quantidadePessoas.equals(""))
            camposValidadosArry = false;

        return camposValidadosArry;
    }


    //*************************************************************************************************************



    public void limpar(View view) {
        limparTudo();
    }

    public void limparTudo() {
        editKmPercurso.setText("");
        editKmLitro.setText("");
        editValorCombustivel.setText("");
        editQuantasPessoas.setText("");
        resultado1.setText("");
        resultado2.setText("");
        resultado3.setText("");
        resultado4.setText("");

        resultado1.setVisibility(View.GONE);
        resultado2.setVisibility(View.GONE);
        resultado3.setVisibility(View.GONE);

    }


    public void calcular(View view) {

        if (validarSwitch.size() == 1) {
            metodo2();

            System.out.println("Metodo 2");

        } else {
            Metodo1();

            System.out.println("Metodo 1");

        }

    }

}


