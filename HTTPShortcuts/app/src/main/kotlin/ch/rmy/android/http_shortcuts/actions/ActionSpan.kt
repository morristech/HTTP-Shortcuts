package ch.rmy.android.http_shortcuts.actions

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.text.style.ReplacementSpan
import kotlin.math.roundToInt

class ActionSpan(private val color: Int, var actionDTO: ActionDTO) : ReplacementSpan() {

    private val typeface by lazy {
        Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
    }

    override fun draw(canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        paint.color = color
        paint.typeface = typeface
        paint.isStrikeThruText = true
        canvas.drawText(text, start, end, x, y.toFloat(), paint)
    }

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        fm?.ascent = paint.fontMetricsInt.ascent
        fm?.bottom = paint.fontMetricsInt.bottom
        fm?.descent = paint.fontMetricsInt.descent
        fm?.leading = paint.fontMetricsInt.leading
        fm?.top = paint.fontMetricsInt.top
        return calculateTextWidth(paint, text.subSequence(start, end))
    }

    private fun calculateTextWidth(paint: Paint, text: CharSequence): Int {
        paint.color = color
        paint.typeface = typeface
        val textWidth = paint.measureText(text, 0, text.length)
        return textWidth.roundToInt()
    }

}