package Jsoup.jsoup

import Jsoup.jsoup.databinding.ActivityMainBinding
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//----------------------------------------------------------------------------------------

        // Write a message to the database
        val database =
            Firebase.database("https://www.worldometers.info/geography/alphabetical-list-of-countries/")
        val myRef = database.getReference("message")

        myRef.setValue("jlkjkjk")


        // --------------------------------------------------------------------------------------------
        getHtmlFromWeb()





    }
        //------------------------------------------------
        fun getHtmlFromWeb() {
            Thread(Runnable {
                var stringBuilder = StringBuilder()

                try {
                    val doc: Document = Jsoup.connect("https://myactivity.google.com/item").get()
                    val links: Elements = doc.select("a[href]")

                    for (link in links) {
                        stringBuilder.append("\n").append("Link: ").append(link.attr("href"))
                            .append("\n").append("Text: ").append(link.text())
                    }

                } catch (e: IOException) {
                    stringBuilder.append("Error : ").append(e.message).append("\n")
                }
                runOnUiThread {
                    binding.ttt.text = stringBuilder.toString()

                    val database =
                        Firebase.database("https://crowdad-ff1ec-default-rtdb.europe-west1.firebasedatabase.app")
                    val myRef = database.getReference("LINKS")

                    myRef.setValue(stringBuilder.toString())

                }
            }).start()


        }
    }




    //-------------------------------------------------------------------------------------------------


