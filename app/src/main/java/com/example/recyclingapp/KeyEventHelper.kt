import android.app.Activity
import android.content.Context
import android.view.KeyEvent
import androidx.appcompat.app.AlertDialog
import kotlin.system.exitProcess

class KeyEventHelper(private val activity: Activity) {

    fun handleOnKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showExitConfirmation()
            return true
        }
        return activity.onKeyDown(keyCode, event)
    }

    private fun showExitConfirmation() {
        AlertDialog.Builder(activity)
            .setTitle("알림")
            .setMessage("어플을 종료하시겠습니까?")
            .setPositiveButton("예") { _, _ ->
                activity.finish()
                exitProcess(0)
            }
            .setNegativeButton("아니오") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
