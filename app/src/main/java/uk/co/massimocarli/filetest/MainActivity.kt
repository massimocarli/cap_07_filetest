package uk.co.massimocarli.filetest

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

  companion object {
    const val FILENAME = "myFile.txt"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }


  fun saveText(view: View) {
    openFileOutput(FILENAME, Context.MODE_PRIVATE).use { outputStream ->
      inputText.text.toString().let {
        it.toByteArray().forEach { currentByte ->
          outputStream.write(currentByte.toInt())
        }
      }
    }
  }

  fun loadText(view: View) {
    openFileInput(FILENAME).use { inputStream ->
      val buffer = ByteArray(1024) { 0 }
      val bis = ByteArrayOutputStream()
      var dataRead = inputStream.read(buffer)
      while (dataRead > 0) {
        bis.write(buffer, 0, dataRead)
        dataRead = inputStream.read(buffer)
      }
      val string = String(bis.toByteArray())
      inputText.setText(string)
      bis.close()
    }
  }
}
