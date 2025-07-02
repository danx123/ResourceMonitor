package com.danx.voidbreaker_launcher

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class MainActivity : AppCompatActivity() {

    private val TAG = "VoidBreaker"
    private val payloadDir: File by lazy { File(filesDir, "payload") }

    private val payloadFiles = listOf(
        "execute.sh",
        "frida_hooks.js",
        "device_props.lua",
        "entropy_profile.json"
    )

    private lateinit var logView: TextView

    companion object {
        init {
            try {
                System.loadLibrary("caiji") // 🔒 Load Frida Gadget stealth
                Log.d("VoidBreaker", "✅ libcaiji.so loaded")
            } catch (e: UnsatisfiedLinkError) {
                Log.e("VoidBreaker", "❌ Failed load libcaiji.so", e)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logView = findViewById(R.id.logView)
        appendLog("🛠️ VoidBreaker siap digunakan.")

        if (!payloadDir.exists()) payloadDir.mkdirs()

        extractFridaHooks()
        copyAssetsExceptFridaHooks()
    }

    fun runPayload(view: View) {
        try {
            val execFile = File(payloadDir, "execute.sh")
            Runtime.getRuntime().exec("sh ${execFile.absolutePath}")
            appendLog("⚡ Payload dijalankan!")
            Toast.makeText(this, "🔥 Payload dijalankan!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            appendLog("❌ Gagal eksekusi payload: ${e.message}")
            Toast.makeText(this, "❌ Gagal eksekusi payload", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "Error exec:", e)
        }
    }

    private fun extractFridaHooks() {
        try {
            val destFile = File(payloadDir, "frida_hooks.js")
            if (!destFile.parentFile.exists()) destFile.parentFile.mkdirs()

            assets.open("frida_hooks.js").use { input ->
                FileOutputStream(destFile).use { output ->
                    input.copyTo(output)
                }
            }
            appendLog("✅ frida_hooks.js extracted")
        } catch (e: IOException) {
            appendLog("❌ Failed extract frida_hooks.js: ${e.message}")
            Log.e(TAG, "Extract error:", e)
        }
    }

    private fun copyAssetsExceptFridaHooks() {
        for (filename in payloadFiles.filter { it != "frida_hooks.js" }) {
            try {
                val input = assets.open(filename)
                val outFile = File(payloadDir, filename)
                val output = FileOutputStream(outFile)

                input.copyTo(output)
                input.close()
                output.close()

                Runtime.getRuntime().exec("chmod 755 ${outFile.absolutePath}")
                appendLog("✅ Copied: $filename")
            } catch (e: IOException) {
                appendLog("❌ Failed copy: $filename → ${e.message}")
                Log.e(TAG, "Copy error:", e)
            }
        }
        Toast.makeText(this, "📦 Payload disiapkan!", Toast.LENGTH_SHORT).show()
    }

    private fun appendLog(msg: String) {
        runOnUiThread {
            val timestamp = "[" + java.text.SimpleDateFormat("HH:mm:ss").format(java.util.Date()) + "]"
            logView.append("$timestamp $msg\n")
            val scroll = logView.layout?.getLineTop(logView.lineCount) ?: 0
            logView.scrollTo(0, scroll)
        }
    }
}
