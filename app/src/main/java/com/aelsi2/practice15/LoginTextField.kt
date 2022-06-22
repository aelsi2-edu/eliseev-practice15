package com.aelsi2.practice15

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat


class  LoginTextField @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {
    object Constants {
        const val passwordIcon = R.drawable.lock
        const val emailIcon = R.drawable.envelope
        const val loginIcon = R.drawable.user
        const val passwordString = R.string.password
        const val confirmPasswordString = R.string.confirm_password
        const val emailString = R.string.email
        const val loginString = R.string.username
        const val textColor = R.color.text_field_color
        const val passwordColor = R.color.password_field_color
        const val fieldBackground = R.drawable.text_field_background
        const val defaultElevationSP : Float = 1f
    }
    val editText : EditText
    val icon : ImageView
    var type: FieldType = FieldType.LOGIN
        set (value) {
        field = value
        setupChildren(field)
    }
    enum class FieldType(val value : Int) {
        LOGIN(0),
        EMAIL(1),
        PASSWORD(2),
        CONFIRM_PASSWORD(3);

        companion object {
            fun fromInt(value: Int) = FieldType.values().first { it.value == value }
        }
    }
    init{
        inflate(context, R.layout.custom_text_field, this)
        editText = findViewById(R.id.text_field)
        icon = findViewById(R.id.icon)
        var fieldType = FieldType.LOGIN
        context.theme.obtainStyledAttributes(attrs,  R.styleable.LoginTextField, 0, 0).apply {
            try {
                fieldType = FieldType.fromInt(getInteger(R.styleable.LoginTextField_field_type, 0))
            } finally {
                recycle()
            }
        }
        type = fieldType
        setupView()
    }
    private fun setupChildren(fieldType : FieldType) {
        when (fieldType) {
            FieldType.LOGIN -> {
                icon.setImageResource(Constants.loginIcon)
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                editText.hint = context.getString(Constants.loginString)
                editText.setTextColor(ContextCompat.getColor(context, Constants.textColor))
            }
            FieldType.PASSWORD -> {
                icon.setImageResource(Constants.passwordIcon)
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                editText.hint = context.getString(Constants.passwordString)
                editText.setTextColor(ContextCompat.getColor(context, Constants.passwordColor))
            }
            FieldType.CONFIRM_PASSWORD -> {
                icon.setImageResource(Constants.passwordIcon)
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                editText.hint = context.getString(Constants.confirmPasswordString)
                editText.setTextColor(ContextCompat.getColor(context, Constants.passwordColor))
            }
            FieldType.EMAIL -> {
                icon.setImageResource(Constants.emailIcon)
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                editText.hint = context.getString(Constants.emailString)
                editText.setTextColor(ContextCompat.getColor(context, Constants.textColor))
            }
        }
    }
    private fun setupView() {
        setBackgroundResource(Constants.fieldBackground)
        elevation = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, Constants.defaultElevationSP, context.resources.displayMetrics)
    }
}