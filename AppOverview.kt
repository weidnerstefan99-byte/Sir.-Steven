
 
import android.content.Context 
import android.graphics.Canvas 
import android.graphics.Color 
import android.graphics.Paint 
import android.view.MotionEvent 
import android.view.View 
 
class BallPhysic( 
    var x: Float, 
    var y: Float, 
    var size: Float, 
    val color: Int 
) { 
    var speedX: Float = 1000f / size 
    var speedY: Float = 1000f / size 
    var savedSpeedX = speedX 
    var savedSpeedY = speedY 
 
    fun ballLogic(width: Int, height: Int) { 
        if (speedX == 0f && speedY == 0f) return 
 
        x += speedX 
        y += speedY 
 
        val radius = size / 2f 
 
        if (x + radius > width) { 
            x = width - radius 
            speedX *= -1 
        } else if (x - radius < 0) { 
            x = radius 
            speedX *= -1 
        } 
        if (y + radius > height) { 
            y = height - radius 
            speedY *= -1 
        } else if (y - radius < 0) { 
            y = radius 
            speedY *= -1
