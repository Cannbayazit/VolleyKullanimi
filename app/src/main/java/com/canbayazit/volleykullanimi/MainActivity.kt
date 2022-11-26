package com.canbayazit.volleykullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //http://canbayazit.online/webservices/delete_kisiler.php
        //kisiSil()
        //kisiEkle()
        //kisiGuncelle()
        //tumKisiler()
        aramaYap()

    }
    fun kisiSil(){

        val url = "http://canbayazit.online/webservices/delete_kisiler.php"
        //Veri göndermek için POST metodunu kullanıyoruz
        val istek = object :StringRequest(Method.POST,url,Response.Listener { cevap ->

            Log.e("Silme İşlemi Cevap", cevap)



        },Response.ErrorListener { e ->  e.printStackTrace()}){

            override fun getParams(): MutableMap<String, String>? {

                val params = HashMap<String,String>()

                params["kisi_id"] = "10"

                return  params
            }
        }

        Volley.newRequestQueue(this@MainActivity).add(istek)

    }

// WEB SERVİCE İNSERT İÇİN GEREKLİ KODLAMA
    fun kisiEkle(){

        val url = "http://canbayazit.online/webservices/insert_kisiler.php"
        val istek = object :StringRequest(Method.POST,url,Response.Listener { cevap ->

            Log.e("Ekleme İşlemi Cevap", cevap)



        },Response.ErrorListener { e ->  e.printStackTrace()}){

            override fun getParams(): MutableMap<String, String>? {

                val params = HashMap<String,String>()

                params["kisi_ad"] = "testad1"
                params["kisi_tel"] = "testel1"

                return  params
            }
        }

        Volley.newRequestQueue(this@MainActivity).add(istek)

    }

    //WEB SERVİCE UPDATE İÇİN GEREKLİ KODLAMA

    fun kisiGuncelle (){


        val url = "http://canbayazit.online/webservices/update_kisiler.php"


        val istek = object :StringRequest(Method.POST,url,Response.Listener { cevap ->

            Log.e("Güncelleme İşlemi Cevap", cevap)



        },Response.ErrorListener { e ->  e.printStackTrace()}){

            override fun getParams(): MutableMap<String, String>? {

                val params = HashMap<String,String>()

                params["kisi_id"] = "15"
                params["kisi_ad"] = "xxxxxxad"
                params["kisi_tel"] = "xxxxxtel"

                return  params
            }
        }

        Volley.newRequestQueue(this@MainActivity).add(istek)

    }
        //WEBN SERVİCE TE SELECT(VERİ OKUMA) İŞLEMİ İÇİN GEREKLİ KODLAMA

        fun tumKisiler() {

            val url = "http://canbayazit.online/webservices/tum_kisiler.php"

            val istek = StringRequest(Request.Method.GET, url, Response.Listener { cevap ->

                Log.e("Veri okuma cevap", cevap)

                try {
                    val jsonObject = JSONObject(cevap)
                    val kisilerListe = jsonObject.getJSONArray("kisiler")

                    for (i in 0 until kisilerListe.length()){

                        val k = kisilerListe.getJSONObject(i)

                        val kisi_id = k.getInt("kisi_id")
                        val kisi_ad = k.getString("kisi_ad")
                        val kisi_tel = k.getString("kisi_tel")

                        Log.e("Kişi id",kisi_id.toString())
                        Log.e("Kişi ad",kisi_ad)
                        Log.e("Kişi tel",kisi_tel)
                        Log.e("**************************","**********************************")

                    }


                }catch (e:JSONException){
                    e.printStackTrace()
                }



            }, Response.ErrorListener { e -> e.printStackTrace() })


                Volley.newRequestQueue(this@MainActivity).add(istek)


        }
        //WEB SERVİCE KULLANILARAK ARAMA İŞLMEİ YAPMAK İÇİN GEREKLİ KODLAMA
    fun aramaYap(){

        val url = "http://canbayazit.online/webservices/tum_kisiler_arama.php"
        //Veri göndermek için POST metodunu kullanıyoruz
        val istek = object :StringRequest(Method.POST,url,Response.Listener { cevap ->

            Log.e("Arama Cevap", cevap)
            try {
                val jsonObject = JSONObject(cevap)
                val kisilerListe = jsonObject.getJSONArray("kisiler")

                for (i in 0 until kisilerListe.length()){

                    val k = kisilerListe.getJSONObject(i)

                    val kisi_id = k.getInt("kisi_id")
                    val kisi_ad = k.getString("kisi_ad")
                    val kisi_tel = k.getString("kisi_tel")

                    Log.e("Kişi id",kisi_id.toString())
                    Log.e("Kişi ad",kisi_ad)
                    Log.e("Kişi tel",kisi_tel)
                    Log.e("**************************","**********************************")

                }


            }catch (e:JSONException){
                e.printStackTrace()
            }

        },Response.ErrorListener { e ->  e.printStackTrace()}){

            override fun getParams(): MutableMap<String, String>? {

                val params = HashMap<String,String>()

                params["kisi_ad"] = "f"

                return  params
            }
        }

        Volley.newRequestQueue(this@MainActivity).add(istek)




    }

}