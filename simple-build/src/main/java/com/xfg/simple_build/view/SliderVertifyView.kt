package com.xfg.simple_build.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.annotation.StringRes
import com.bumptech.glide.Glide
import com.example.simple_build.R

/**
 * 滑动验证组合控件
 */
class SliderVertifyView : LinearLayout {

    private var background: ImageView? = null
    private var slide: ImageView? = null
    private var seekBar: SeekBar? = null
    private var listener: OnSlideListener? = null
    private var tvSeekBarDes:TextView? = null
    private var precision = 0
    private var judgex = 0
    private var judgey = 0

    private var scaleX = 1f
    private var scaleY = 1f


    constructor(context: Context) : super(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    private fun initView(context: Context) {
        LayoutInflater.from(context).inflate(
            R.layout.layout_slider, this,
            true
        )
        background = findViewById<ImageView>(R.id.slider_bg)
        slide = findViewById<ImageView>(R.id.slider)
        seekBar = findViewById<SeekBar>(R.id.seekabr)
        tvSeekBarDes = findViewById(R.id.tv_seekbar_des)
        seekBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val x = progress / 100f * (seekBar.width - seekBar.thumb.intrinsicWidth)
                slide!!.let {
                    val y: Float = it.y

                    // 更新滑块的位置
                    it.x = x
                    it.y = y
                    invalidate()
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                slide!!.let {
                    if (it.x / scaleX <= judgex + precision && it.getX() / scaleX >= judgex - precision) {
                        if (listener != null) {
                            listener!!.onSlideListener(
                                (it.x / scaleX).toInt(),
                                it.y.toInt()
                            )
                        }
                    } else {
                        seekBar.progress = 0
                        it.x = 0f
                        invalidate()
                    }
                }

            }
        })
    }

    /**
     * 设置背景大图
     */
    fun setBackground(path: String?): SliderVertifyView {
        background?.let { Glide.with(context).load(path).dontAnimate().into(it) }
        return this
    }

    fun setBackground(bitmap: Bitmap?): SliderVertifyView {
        background!!.setImageBitmap(bitmap)
        return this
    }

    fun setSlide(path: String?): SliderVertifyView {
        slide?.let { Glide.with(context).load(path).dontAnimate().into(it) }
        return this
    }

    fun setSlide(bitmap: Bitmap?):SliderVertifyView {
        slide?.setImageBitmap(bitmap)
        return this
    }

    fun setThumb(drawable: Drawable?): SliderVertifyView{
        seekBar?.thumb = drawable
        return this
    }

    fun setSlide(
        bitmap: Bitmap?,
        backgroundWidth: Int,
        backgroundHeight: Int,
        x: Int,
        y: Int
    ): SliderVertifyView {
        slide?.setImageBitmap(bitmap)
        judgex = x
        judgey = y
        slide?.post(Runnable {
            scaleX = background!!.measuredWidth.toFloat() / backgroundWidth.toFloat()
            scaleY = background!!.measuredHeight.toFloat() / backgroundHeight.toFloat()
            slide?.setY(y * scaleY)
        })
        return this
    }

    fun setPrecision(precision: Int): SliderVertifyView {
        this.precision = precision
        return this
    }

    fun setListener(listener: OnSlideListener?): SliderVertifyView {
        this.listener = listener
        return this
    }

    fun setSeekBarDes(string: String):SliderVertifyView{
        tvSeekBarDes?.text = string
        return this
    }

    fun setSeekBarDes(@StringRes string: Int):SliderVertifyView{
        tvSeekBarDes?.text = context.getText(string)
        return this
    }

    interface OnSlideListener {
        fun onSlideListener(v: Int, top: Int)
    }

}