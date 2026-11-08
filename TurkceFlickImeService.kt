package com.example.turkceflick

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputConnection
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.math.abs

class TurkceFlickImeService : InputMethodService() {
    private val groups = listOf(
        listOf("1", ".", ",", "?", "!"),
        listOf("2", "a", "b", "c", "ç"),
        listOf("3", "d", "e", "f", "ğ"),
        listOf("4", "g", "h", "ı", "i"),
        listOf("5", "j", "k", "l", "m"),
        listOf("6", "n", "o", "ö", "p"),
        listOf("7", "r", "s", "ş", "t"),
        listOf("8", "u", "ü", "v", "y"),
        listOf("9", "z", "x", "q", "w"),
        listOf("*", "@", "#", "(", ")"),
        listOf("0", "-", "'", "\"", ":")
    )

    override fun onCreateInputView(): View {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(5), dp(6), dp(5), dp(8))
            setBackgroundColor(Color.rgb(20, 24, 30))
        }

        val hint = TextView(this).apply {
            text = "Türkçe Flick • Tuşa dokun veya kaydır"
            textSize = 12f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, dp(6))
        }
        root.addView(hint, LinearLayout.LayoutParams(-1, dp(24)))

        val rows = listOf(groups.subList(0, 3), groups.subList(3, 6),
                         groups.subList(6, 9), groups.subList(9, 11))
        rows.forEachIndexed { rowIndex, row ->
            val line = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
            }
            row.forEach { group ->
                val key = makeKey(group)
                val lp = LinearLayout.LayoutParams(0, dp(55), 1f).apply {
                    setMargins(dp(3), dp(3), dp(3), dp(3))
                }
                line.addView(key, lp)
            }
            root.addView(line, LinearLayout.LayoutParams(-1, dp(61)))
        }

        val bottom = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        bottom.addView(makeActionKey("⌫", 1f) { currentInputConnection?.deleteSurroundingText(1, 0) },
            LinearLayout.LayoutParams(0, dp(48), 1f).apply { setMargins(dp(3), dp(2), dp(3), dp(2)) })
        bottom.addView(makeActionKey("Boşluk", 2.5f) { currentInputConnection?.commitText(" ", 1) },
            LinearLayout.LayoutParams(0, dp(48), 2.5f).apply { setMargins(dp(3), dp(2), dp(3), dp(2)) })
        bottom.addView(makeActionKey("↵", 1f) {
            val ic: InputConnection? = currentInputConnection
            ic?.let { if (!it.performEditorAction(android.view.inputmethod.EditorInfo.IME_ACTION_GO)) it.commitText("\n", 1) }
        }, LinearLayout.LayoutParams(0, dp(48), 1f).apply { setMargins(dp(3), dp(2), dp(3), dp(2)) })
        root.addView(bottom)

        return root
    }

    private fun makeKey(group: List<String>): TextView {
        val main = group[1].uppercase()
        val sub = group.drop(2).joinToString(" ")
        return TextView(this).apply {
            text = if (group.size > 2) "$main\n${group.drop(1).joinToString(" ")}" else main
            textSize = 17f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT
            background = rounded(Color.rgb(44, 51, 61))
            isClickable = true
            isFocusable = true
            var downX = 0f
            var downY = 0f
            setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        downX = event.x
                        downY = event.y
                        v.alpha = 0.75f
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        v.alpha = 1f
                        val dx = event.x - downX
                        val dy = event.y - downY
                        val index = if (abs(dx) < dp(18) && abs(dy) < dp(18)) 1 else {
                            when {
                                abs(dx) > abs(dy) && dx > 0 -> 2
                                abs(dx) > abs(dy) && dx < 0 -> 3
                                dy < 0 -> 4
                                else -> 0
                            }
                        }
                        val chosen = group.getOrNull(index) ?: group[1]
                        currentInputConnection?.commitText(chosen, 1)
                        true
                    }
                    MotionEvent.ACTION_CANCEL -> { v.alpha = 1f; true }
                    else -> true
                }
            }
            contentDescription = "Tuş ${group[0]}, karakterler ${group.drop(1).joinToString()}"
        }
    }

    private fun makeActionKey(label: String, weight: Float, action: () -> Unit): TextView =
        TextView(this).apply {
            text = label
            textSize = if (label == "Boşluk") 15f else 19f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
            background = rounded(if (label == "↵") Color.rgb(45, 111, 230) else Color.rgb(44, 51, 61))
            setOnClickListener { action() }
        }

    private fun rounded(color: Int) = GradientDrawable().apply {
        setColor(color)
        cornerRadius = dp(9).toFloat()
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}
