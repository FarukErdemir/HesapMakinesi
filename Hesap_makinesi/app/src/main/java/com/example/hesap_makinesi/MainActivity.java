package com.example.hesap_makinesi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView sonuc,cozum;
    Button bol ,carp,topla,cikar,esittir;
    Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button0;
    Button buttonAC,buttonvirgul;
    Button buttonsil,buttonparantezac,buttonparantezkapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sonuc=findViewById(R.id.Sonuc);
        cozum=findViewById(R.id.Cozum);

        assign(buttonsil,R.id.button_Sil);
        assign(buttonparantezac,R.id.button_Parantezac);
        assign(buttonparantezkapa,R.id.button_Parantezkapa);
        assign(buttonAC,R.id.button_AC);
        assign(buttonvirgul,R.id.button_Virgul);
        assign(button1,R.id.button_1);
        assign(button2,R.id.button_2);
        assign(button3,R.id.button_3);
        assign(button4,R.id.button_4);
        assign(button5,R.id.button_5);
        assign(button6,R.id.button_6);
        assign(button7,R.id.button_7);
        assign(button8,R.id.button_8);
        assign(button9,R.id.button_9);
        assign(button0,R.id.button_0);
        assign(bol,R.id.button_Bol);
        assign(carp,R.id.button_Carp);
        assign(topla,R.id.button_Topla);
        assign(cikar,R.id.button_Cikart);
        assign(esittir,R.id.button_Esittir);

    }

    public void assign(Button btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public  void onClick(View view){

        Button button =(Button) view;
        String buttontext =button.getText().toString();
        String hesapla=cozum.getText().toString();

        if (buttontext.equals(("AC"))){
            cozum.setText("");
            sonuc.setText("0");
            return;
        }
        if (buttontext.equals("=")){
            cozum.setText((sonuc.getText()));
            return;
        }
        if (buttontext.equals(("SİL"))){
            hesapla=hesapla.substring(0,hesapla.length()-1);
        }
        else{
            hesapla=hesapla+buttontext;
        }
        cozum.setText((hesapla));
        String sonsonuc =sonucal(hesapla);
        if (!sonsonuc.equals(("Hata"))){
            sonuc.setText(sonsonuc);
        }
    }
    String sonucal(String veri){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String sonsonuc = context.evaluateString(scriptable,veri,"Javascript",1,null).toString();
            if (sonsonuc.endsWith(".0")){
                sonsonuc=sonsonuc.replace(".0","");
            }
            return sonsonuc;
        }
        catch (Exception e){
            return "Hata";
        }
    }
}