package com.movie

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import kotlin.math.min


class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {
    private var rad = 15f
    private var len = 100f
    private var corner = 0f
    private var dCorner = 1f
    private var center = Point()
    private val rect1 = RectF()
    private val rect2 = RectF()
    private var newRad = 15f
    private var isIncreased = 0
    private var number = 1
    private var rad1 = rad
    private var rad2 = rad
    private var rad3 = rad
    private var rad4 = rad

    private val color = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#dce0e3")
    }

    override fun onSaveInstanceState(): Parcelable {
        val stateOfView = CustomState(super.onSaveInstanceState())
        stateOfView.corner = corner
        stateOfView.dCorner = dCorner
        stateOfView.newRad = newRad
        stateOfView.isIncreased = isIncreased
        stateOfView.number = number
        stateOfView.rad1 = rad1
        stateOfView.rad2 = rad2
        stateOfView.rad3 = rad3
        stateOfView.rad4 = rad4
        return stateOfView
    }

    override fun onRestoreInstanceState(stateOfView: Parcelable?) {
        stateOfView as CustomState
        super.onRestoreInstanceState(stateOfView.superState)
        corner = stateOfView.corner
        dCorner = stateOfView.dCorner
        newRad = stateOfView.newRad
        isIncreased = stateOfView.isIncreased
        number = stateOfView.number
        rad1 = stateOfView.rad1
        rad2 = stateOfView.rad2
        rad3 = stateOfView.rad3
        rad4 = stateOfView.rad4
    }

    private class CustomState : BaseSavedState {
        var corner = 0f
        var dCorner = 1f
        var newRad = 15f
        var isIncreased = 0
        var number = 1
        var rad1 = 15f
        var rad2 = 15f
        var rad3 = 15f
        var rad4 = 15f

        constructor(superState: Parcelable?) : super(superState)
        constructor(parcel: Parcel) : super(parcel) {
            corner = parcel.readFloat()
            dCorner = parcel.readFloat()
            newRad = parcel.readFloat()
            isIncreased = parcel.readInt()
            number = parcel.readInt()
            rad1 = parcel.readFloat()
            rad2 = parcel.readFloat()
            rad3 = parcel.readFloat()
            rad4 = parcel.readFloat()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeFloat(corner)
            out.writeFloat(dCorner)
            out.writeFloat(newRad)
            out.writeInt(isIncreased)
            out.writeInt(number)
            out.writeFloat(rad1)
            out.writeFloat(rad2)
            out.writeFloat(rad3)
            out.writeFloat(rad4)
        }

        companion object {
            @JvmField
            val CREATOR = object : Parcelable.Creator<CustomState> {
                override fun createFromParcel(source: Parcel): CustomState = CustomState(source)
                override fun newArray(size: Int): Array<CustomState?> = arrayOfNulls(size)
            }
        }
    }

    init {
        val a: TypedArray = context.obtainStyledAttributes(
            attrs, R.styleable.CustomView, defStyleAttr, defStyleRes
        )
        try {
            rad = a.getFloat(R.styleable.CustomView_rad, 15f)
            len = a.getFloat(R.styleable.CustomView_len, 80f)
        } finally {
            a.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val defWidth = 420
        val defHeight = 220

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val width: Int = when (widthMode) {
            MeasureSpec.EXACTLY -> { widthSize }
            MeasureSpec.AT_MOST -> { min(defWidth, widthSize) }
            else -> { defWidth }
        }

        val height: Int = when (heightMode) {
            MeasureSpec.EXACTLY -> { heightSize }
            MeasureSpec.AT_MOST -> { min(defHeight, heightSize) }
            else -> { defHeight }
        }

        center.x = width / 2
        center.y = height / 2
        //len = min(min(height.toFloat() / 2, len), width / 4)
        //rad = min(len / 4, rad)

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        rect1.set(
            (center.x / 2 - rad),
            (center.y + len), (center.x / 2 + rad), (center.y - len)
        )

        rect2.set(
            (center.x / 2 - len),
            (center.y + rad), (center.x / 2 + len), (center.y - rad)
        )

        animatePlus(canvas)
        animateCircles(canvas)
        postInvalidateOnAnimation()
    }

    private fun animatePlus(canvas: Canvas?) {
        val save = canvas?.save()

        if (corner <= 360f) dCorner *= 1.01f
        else dCorner /= 1.01f
        corner += dCorner

        canvas?.rotate(corner, center.x.toFloat() / 2, center.y.toFloat())

        if (corner > 720f) {
            corner -= 720f
            dCorner = 1f
        }

        canvas?.drawRoundRect(rect1, 50f, 50f, color)
        canvas?.drawRoundRect(rect2, 50f, 50f, color)
        save?.let { canvas.restoreToCount(it) }
    }

    private fun animateCircles(canvas: Canvas?) {
        val save = canvas?.save()

        if (isIncreased == 0) {
            if (newRad < 3 * rad / 2) newRad += 0.25f
            else isIncreased = 1
        } else {
            if (newRad > rad) newRad -= 0.25f
            else {
                isIncreased = 0
                if (number == 4) {
                    number = 1
                } else {
                    number++
                }
            }
        }

        when (number) {
            1 -> { rad1 = newRad }
            2 -> { rad2 = newRad }
            3 -> { rad3 = newRad }
            else -> { rad4 = newRad }
        }

        canvas?.drawCircle(
            center.x.toFloat() * 3 / 2,
            center.y.toFloat() - len + rad * 3 / 2,
            rad1,
            color
        )

        canvas?.drawCircle(
            center.x.toFloat() * 3 / 2 + len - rad * 3 / 2,
            center.y.toFloat(),
            rad2,
            color
        )

        canvas?.drawCircle(
            center.x.toFloat() * 3 / 2,
            center.y.toFloat() + len - rad * 3 / 2,
            rad3,
            color
        )

        canvas?.drawCircle(
            center.x.toFloat() * 3 / 2 - len + rad * 3 / 2,
            center.y.toFloat(),
            rad4,
            color
        )

        save?.let { canvas.restoreToCount(it) }
    }

}