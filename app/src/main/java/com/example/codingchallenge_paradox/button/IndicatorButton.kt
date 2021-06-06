package com.example.codingchallenge_paradox.button

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import androidx.core.view.children
import com.example.codingchallenge_paradox.R
import kotlinx.android.synthetic.main.button_item.view.*

@Suppress("LeakingThis")
open class IndicatorButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.button_item, this, true)

        context.theme.obtainStyledAttributes(attrs, R.styleable.IndicatorButton, 0, 0).use {
            imageView.setImageDrawable(it.getDrawable(R.styleable.IndicatorButton_icon))
            main_text.text = it.getText(R.styleable.IndicatorButton_title)
            sub_text.text = it.getText(R.styleable.IndicatorButton_subtitle)
        }
        isClickable = true
        isFocusable = true
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        children.forEach { it.isEnabled = enabled }
    }
}