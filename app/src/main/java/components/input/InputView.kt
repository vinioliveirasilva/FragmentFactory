package components.input

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.example.fragmentfactory.R

class InputView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    lateinit var title: String
    lateinit var hint: String
    var text: String = ""

    init {

        inflate(context, R.layout.component_input_view, this)

        context.obtainStyledAttributes(attrs, R.styleable.InputView, 0, 0).run {
            this.getString(R.styleable.InputView_title)?.run { title = this }
            this.getString(R.styleable.InputView_hint)?.run { hint = this }
        }

        this.findViewById<TextView>(R.id.tv_input_hint).text = title
        this.findViewById<EditText>(R.id.et_input).apply {
            hint = hint
            doOnTextChanged { currentText, _, _, _ -> this@InputView.text = currentText.toString() }
        }
    }
}