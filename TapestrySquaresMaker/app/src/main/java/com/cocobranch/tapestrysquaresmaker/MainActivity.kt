package com.cocobranch.tapestrysquaresmaker

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.cocobranch.tapestrysquaresmaker.databinding.ActivityMainBinding
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.PageSize
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val PERMISSION_REQUEST_CODE = 100
    // Activity result launcher for selecting an image from the gallery
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("MyLogger", "Instantiating app")
        binding.apply {
            // Initialize the activity result launcher
            galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    data?.data?.let { uri ->
                        Log.d("MyLogger", "Setting Bitmap")
                        // Set the selected image to the ImageView
                        imageView.setImageURI(uri)
                        val bitmap = imageView.drawable.toBitmap()
                        Log.d("MyLogger", "Creating final product")
                        imageView.setImageBitmap(pixelateImage(bitmap, etPixel.text.toString().toInt())) // You can adjust the pixelSize as desired.
                    }
                }
            }

            // Button click listener to select an image from the gallery
            btnUpload.setOnClickListener {
                openGallery()
            }
            btnExportPdf.setOnClickListener {
                Log.d("MyLogger", "Export clicked")
                exportAsPdf(etPixel.text.toString().toInt())
            }
        }


    }

    private fun openGallery() {
        // Create an intent to pick an image from the gallery
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        // Launch the gallery activity with the intent
        galleryLauncher.launch(intent)
        Log.d("MyLogger", "Opening Gallery")
    }

    // Function to pixelate the image
    private fun pixelateImage(bitmap: Bitmap, pixelSize: Int): Bitmap {
        // Define the available colors
        val availableColors = listOf(Color.BLACK, Color.WHITE, Color.YELLOW, Color.BLUE, Color.RED, Color.GREEN)

        val pixelatedBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(pixelatedBitmap)
        val paint = Paint()

        // Loop through the bitmap pixels and draw pixelSize x pixelSize squares with the closest available color
        for (x in 0 until bitmap.width step pixelSize) {
            for (y in 0 until bitmap.height step pixelSize) {
                // Calculate the mean color for the current pixelSize x pixelSize square
                var totalRed = 255
                var totalGreen = 255
                var totalBlue = 255
                //var totalPixels = 0
                for (i in 0 until pixelSize) {
                    for (j in 0 until pixelSize) {
                        if (x + i < bitmap.width && y + j < bitmap.height) {
                            val pixelColor = bitmap.getPixel(x + i, y + j)
                            if (totalRed > Color.red(pixelColor)){
                                totalRed = Color.red(pixelColor)
                            }
                            if (totalGreen > Color.green(pixelColor)){
                                totalGreen = Color.green(pixelColor)
                            }
                            if (totalBlue > Color.blue(pixelColor)){
                                totalBlue = Color.blue(pixelColor)
                            }
                            //totalRed += Color.red(pixelColor)
                            //totalGreen += Color.green(pixelColor)
                            //totalBlue += Color.blue(pixelColor)
                            //totalPixels++
                        }
                    }
                }
                val meanRed = totalRed
                val meanGreen = totalGreen
                val meanBlue = totalBlue
                val meanColor = Color.rgb(meanRed, meanGreen, meanBlue)
                // Find the closest available color to the mean color
                var closestColor = availableColors[0]
                var minDistance = colorDistance(meanColor, availableColors[0])

                for (color in availableColors) {
                    val distance = colorDistance(meanColor, color)
                    if (distance < minDistance) {
                        minDistance = distance
                        closestColor = color
                    }
                }
                // Draw the pixelSize x pixelSize square with the closest available color
                paint.color = closestColor
                paint.style = Paint.Style.FILL
                canvas.drawRect(Rect(x, y, x + pixelSize, y + pixelSize), paint)

                // Draw the black border around the square
                paint.color = Color.BLACK
                paint.style = Paint.Style.STROKE
                paint.strokeWidth = 2f
                canvas.drawRect(Rect(x, y, x + pixelSize, y + pixelSize), paint)
            }
        }

        return pixelatedBitmap
    }

    // Function to calculate the Euclidean distance between two colors
    private fun colorDistance(color1: Int, color2: Int): Double {
        val r1 = Color.red(color1)
        val g1 = Color.green(color1)
        val b1 = Color.blue(color1)

        val r2 = Color.red(color2)
        val g2 = Color.green(color2)
        val b2 = Color.blue(color2)

        return Math.sqrt((r1 - r2).toDouble().pow(2.0) + (g1 - g2).toDouble().pow(2.0) + (b1 - b2).toDouble().pow(2.0))
    }

    private fun exportAsPdf(pixelSize: Int) {
        val bitmap = binding.imageView.drawable.toBitmap()
        Log.d("MyLogger", "Entering function exportAsPdf")
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d("MyLogger", "Permission not granted")
            // Request the permission to write to external storage if not granted
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
            Log.d("MyLogger", "Asking permissions")
            return
        }
        // Continue with PDF export once the permission is granted
        Log.d("MyLogger", "Starting exporting")
        savePdfToDownloads(bitmap, pixelSize)
    }

    private fun savePdfToDownloads(bitmap: Bitmap, pixelSize: Int) {
        val pdfFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "pixelated_image.pdf")

        val document = Document(PageSize.A4)
        try {
            PdfWriter.getInstance(document, FileOutputStream(pdfFile))
            document.open()
            Log.d("MyLogger", "Just about to export: ${pixelSize}")
            //val image = Image.getInstance(bitmapToByteArray(pixelateImage(bitmap, pixelSize)))
            val image = Image.getInstance(bitmapToByteArray(bitmap))
            image.alignment = Image.MIDDLE
            Log.d("MyLogger", "Just exported")
            // Set the size of the image in the PDF (you can adjust these values as needed)
            image.scaleToFit(PageSize.A4.width - 20, PageSize.A4.height - 20)

            document.add(image)
        } catch (e: Exception) {
            Log.d("MyLogger", "Error")
            e.printStackTrace()
        } finally {
            Log.d("MyLogger", "Finally")
            document.close()
        }

        Toast.makeText(this, "Image exported as PDF: ${pdfFile.absolutePath}", Toast.LENGTH_SHORT).show()
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

}