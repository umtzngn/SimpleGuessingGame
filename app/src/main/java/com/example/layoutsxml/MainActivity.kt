package com.example.layoutsxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.layoutsxml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sonucGosterButtom.setOnClickListener {
            hileIleHesapla()
        }



    }

    fun hileIleHesapla(){

        val tahminDegeri = (binding.tahminDegeriText.text.toString()).toDoubleOrNull()
        if(tahminDegeri == null) {
            binding.sonucGosterTextView.text=""
            Toast.makeText(this, "Lütfen 1 ile 20 arasında bir değer giriniz!", Toast.LENGTH_SHORT).show()
            return
        }else if (tahminDegeri > 20){
            Toast.makeText(this, "Lütfen 1 ile 20 arasında bir değer giriniz!", Toast.LENGTH_SHORT).show()
            return
        }else if (tahminDegeri <=0){
            Toast.makeText(this, "Lütfen 1 ile 20 arasında bir değer giriniz!", Toast.LENGTH_SHORT).show()
            return
        }

        val secilenRadioButton = binding.hileSecenekleriRadioGroup.checkedRadioButtonId

        val hileYuzdesi = when (secilenRadioButton){
            R.id.yaziklarOlsunRadioButton -> 0.80
            R.id.duzenbazRadioButton ->0.70
            else -> 0.50
        }

        val hileAcilsin = binding.hileAcilsinmiSwitch.isChecked



        if (hileAcilsin){

            val altDeger = tahminDegeri*hileYuzdesi
            var ustDeger = tahminDegeri*(1-hileYuzdesi)+tahminDegeri

            if (ustDeger>20){
                ustDeger= 20.0
            }

            val random = random(altDeger.toInt(),ustDeger.toInt())
            val randomSayi = random.randomHesapla()

            binding.sonucGosterTextView.text=getString(R.string.sonucu_goster_text,randomSayi.toString())
            if (tahminDegeri.toInt()==randomSayi){
                Toast.makeText(this, "Harika!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Canın Sağolsun!", Toast.LENGTH_SHORT).show()
            }


        }else{
            val random =random(1,20)
            val randomSayi=random.randomHesapla()

            binding.sonucGosterTextView.text=getString(R.string.sonucu_goster_text,randomSayi.toString())

        }




    }


    class random(var numAlt:Int, val numUst:Int){
        fun randomHesapla():Int{
            return (numAlt..numUst).random()
        }
    }



}