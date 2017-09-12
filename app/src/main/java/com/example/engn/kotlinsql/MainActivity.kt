package com.example.engn.kotlinsql

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    internal var helper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addData()

        viewAll()


        UpdateData()
        DeleteData()




    }

    fun addData() {

        buttonekle.setOnClickListener {

            helper.insertData(editad.text.toString(),
                    editsoyad.text.toString(),
                    editnot.text.toString())
        }
    }

    fun UpdateData() {
        buttonguncelle.setOnClickListener {
            val isUpdate = helper.updateData(editid.text.toString(),
                    editad.text.toString(),
                    editsoyad.text.toString(), editnot.text.toString())
            if (isUpdate == true)
                Toast.makeText(this@MainActivity, "Veriler Güncellendi..", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this@MainActivity, "Veriler Güncellenmedi!..", Toast.LENGTH_LONG).show()
        }
    }


    fun DeleteData(){
        buttonsil.setOnClickListener {

            helper.deleteData(editid.text.toString())

        }

    }







    fun viewAll() {
        butongoster.setOnClickListener(
                View.OnClickListener {
                    val res = helper.allData
                    if (res.count == 0) {

                        showMessage("Error", "Veriler Bulunamadı..")
                        return@OnClickListener
                    }

                    val buffer = StringBuffer()
                    while (res.moveToNext()) {
                        buffer.append("Id :" + res.getString(0) + "\n")
                        buffer.append("Ad :" + res.getString(1) + "\n")
                        buffer.append("Soyad :" + res.getString(2) + "\n")
                        buffer.append("Not :" + res.getString(3) + "\n\n")
                    }


                    showMessage("Veriler", buffer.toString())
                }
        )
    }



    fun showMessage(title : String,Message : String){

        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()



    }

}
