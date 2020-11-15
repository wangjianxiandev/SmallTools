package com.wjx.android.smalltools.common

import com.wjx.android.smalltools.common.ext.close
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/11/16 0:20
 */
object ShellUtils {
    fun execCommand(
        command: List<String?>,
        isRoot: Boolean
    ): CommandResult {
        return execCommand(
            command.toTypedArray(),
            isRoot
        )
    }

    fun execCommand(command: String?, isRoot: Boolean): CommandResult {
        return execCommand(arrayOf(command), isRoot)
    }

    fun execCommand(commands: Array<String?>, isRoot: Boolean): CommandResult {
        var result = -1
        val resultBuilder = StringBuilder()
        val errorBuilder = StringBuilder()
        var process: Process? = null
        var os: DataOutputStream? = null
        var resultReader: BufferedReader? = null
        var errorReader: BufferedReader? = null
        try {
            process = Runtime.getRuntime().exec(if (isRoot) "su" else "sh")
            os = DataOutputStream(process.outputStream)
            for (command in commands) {
                os.writeBytes(command)
                os.writeBytes("\n")
                os.flush()
            }
            os.writeBytes("exit\n")
            os.flush()
            var line: String?
            result = process.waitFor()
            resultReader = BufferedReader(
                InputStreamReader(
                    process.inputStream,
                    Charset.forName("UTF-8")
                ), 4096
            )
            while (resultReader.readLine().also { line = it } != null) {
                resultBuilder.append(line)
            }
            errorReader = BufferedReader(
                InputStreamReader(
                    process.errorStream,
                    Charset.forName("UTF-8")
                ), 4096
            )
            while (errorReader.readLine().also { line = it } != null) {
                errorBuilder.append(line)
            }
        } catch (e: IOException) {
            errorBuilder.append("\n")
                .append(e.message)
        } catch (e: InterruptedException) {
            errorBuilder.append("\n")
                .append(e.message)
        } finally {
            close(os!!, resultReader!!, errorReader!!)
            process?.destroy()
        }
        return CommandResult(resultBuilder.toString(), errorBuilder.toString(), result)
    }

    class CommandResult internal constructor(
        var successResult: String,
        var errorResult: String,
        // 0  normal termination
        var result: Int
    ) {

        override fun toString(): String {
            return "CommandResult{" +
                    "successResult='" + successResult + '\'' +
                    ", errorResult='" + errorResult + '\'' +
                    ", result=" + result +
                    '}'
        }

    }
}
