package com.steven.game 
 
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
 
        } 
    } 
} 
 
class GameView(context: Context) : View(context) {    private val paint = Paint() 
    private var stop = true 
    private var start = Color.rgb(0, 180, 0) 
    private var buttonFarbe = start 
 
 
    private fun createBall(size: Float, color: Int): BallPhysic { 
        val radius = size / 2f 
        return BallPhysic(0f + radius, 0f + radius, size, color) 
    } 
 
 
    private val balls = listOf( 
        createBall(800f, Color.BLACK), 
        createBall(400f, Color.RED), 
        createBall(200f, Color.YELLOW), 
        createBall(100f, Color.GREEN), 
        createBall(50f, Color.BLUE), 
        createBall(25f, Color.WHITE), 
    ) 
 
    override fun onDraw(canvas: Canvas) { 
        super.onDraw(canvas) 
        // el hintagründ 
        canvas.drawColor(Color.rgb(0, 180, 255)) 
 
        // big balls 
        /*for (ball in balls) { 
            ball.ballLogic(width, height) 
            paint.color = ball.color 
            canvas.drawCircle(ball.x, ball.y, ball.size / 2f, paint) 
        }*/ 
 
            // Würfel modus: Ziel, mit einem Knopf umstellen 
            /*for (ball in balls) { 
            ball.ballLogic(width, height)
 
            paint.color = ball.color 
 
            val halfSize = ball.size / 2f 
            val left = ball.x - halfSize 
            val top = ball.y - halfSize 
            val right = ball.x + halfSize 
            val bottom = ball.y + halfSize 
 
            canvas.drawRect(left, top, right, bottom, paint) 
        }*/ 
 
 
        // Halb-Würfel modus: Ziel, mit einem Knopf umstellen 
        for (ball in balls) { 
            // Nur wenn stop == 0 ist, bewegen sich die Bälle 
            if (!stop) { 
                ball.ballLogic(width, height) 
            } 
 
            paint.color = ball.color 
            val halfSize = ball.size / 2f 
            val radius = ball.size * 0.25f 
            canvas.drawRoundRect( 
                ball.x - halfSize, ball.y - halfSize, 
                ball.x + halfSize, ball.y + halfSize, 
                radius, radius, paint 
            ) 
        } 
 
 
 
        // el konpfo 
        paint.color = buttonFarbe 
        val bl = width * 0.2f 
        val bt = height * 0.8f 
        val br = width * 0.8f 
        val bb = height * 0.95f 
 
        canvas.drawRoundRect(bl, bt, br, bb, 50f, 50f, paint) 
        paint.color = Color.BLACK

      
        paint.color = Color.BLACK 
        paint.textSize = 100f 
        paint.textAlign = Paint.Align.CENTER 
 
        val fontMetrics = paint.fontMetrics 
        val verticalOffset = (fontMetrics.descent + fontMetrics.ascent) / 2 
        val yPos = (height * 0.875f) - verticalOffset 
 
        val buttonText = if (!stop) "STOP" else "START" 
        // el text de knopfo 
        canvas.drawText(buttonText, width * 0.5f, yPos, paint) 
 
 
        postInvalidateDelayed(1) 
    } 
 
 
    override fun onTouchEvent(event: MotionEvent): Boolean { 
        if (event.action == MotionEvent.ACTION_DOWN) { 
 
            val left = width * 0.2f 
            val top = height * 0.8f 
            val right = width * 0.8f 
            val bottom = height * 0.95f 
 
            val bhitbox = event.x in left..right && event.y in top..bottom 
 
 
            if (bhitbox) { 
                if (!stop) { 
                    for (ball in balls) { 
                        stop = true 
                        ball.savedSpeedX = ball.speedX 
                        ball.savedSpeedY = ball.speedY 
                        ball.speedX = 0f 
                        ball.speedY = 0f 
                        buttonFarbe = start 
                    } 
                } else { 
                    for (ball in balls) {
                      
                        stop = false 
                        ball.speedX = ball.savedSpeedX 
                        ball.speedY = ball.savedSpeedY 
                        buttonFarbe = Color.rgb(180, 0, 0) 
                    } 
                } 
 
            } 
        } 
        return true 
    } 
} 
            
