package com.example.tangogo.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

object PdfUtils {

    fun openPdfFromAssets(context: Context, fileName: String) {
        try {
            // Copy the PDF from assets to cache
            val assetManager = context.assets
            val inputStream = assetManager.open(fileName)
            val outFile = File(context.cacheDir, fileName)

            inputStream.use { input ->
                FileOutputStream(outFile).use { output ->
                    input.copyTo(output)
                }
            }

            // Generate content URI using FileProvider
            val uri: Uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider", // Must match authority in Manifest
                outFile
            )

            // Launch PDF viewer
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/pdf")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            // Start the activity
            context.startActivity(intent)

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Unable to open PDF: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
