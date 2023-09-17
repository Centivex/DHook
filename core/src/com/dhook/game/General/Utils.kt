package com.dhook.game.General

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.math.*
import com.badlogic.gdx.scenes.scene2d.*
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Cell
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.JsonValue
import com.badlogic.gdx.utils.Pools
import com.badlogic.gdx.utils.viewport.Viewport
import java.util.*
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.reflect.KClass

/** Created by Darius on 17/09/2023. */
object Utils {
    /**
     * Length (angular) of a shortest way between two angles.
     * It will be in range [-180, 180] (signed).
     */
    @JvmStatic
    fun getAngleDifferenceSigned(sourceAngle: Float, targetAngle: Float): Float {
        val thisSourceAngle = Math.toRadians(sourceAngle.toDouble())
        val thisTargetAngle = Math.toRadians(targetAngle.toDouble())
        return Math.toDegrees(atan2(sin((thisTargetAngle - thisSourceAngle)), cos((thisTargetAngle - thisSourceAngle)))).toFloat()
    }

    @JvmStatic
    fun getAngleDifferenceNotSigned(sourceAngle: Float, targetAngle: Float): Float {
        return abs(getAngleDifferenceSigned(sourceAngle, targetAngle))
    }

    /** Clamps input values to input range, then maps them to the output range.
     *
     * inLow must be lower than inHigh, but outLow can be bigger than outHigh  */
    fun map(input: Number, inLow: Number, inHigh: Number, outLow: Number, outHigh: Number,
            interpolation: Interpolation = Interpolation.linear): Float {
        return map(input.toFloat(), inLow.toFloat(), inHigh.toFloat(), outLow.toFloat(), outHigh.toFloat(), interpolation)
    }

    /** @see map */
    fun map(input: Float, inLow: Float, inHigh: Float, outLow: Float, outHigh: Float,
            interpolation: Interpolation = Interpolation.linear): Float {
        var thisOutputLow = outLow
        var thisOutputHigh = outHigh
        if (input < inLow) return thisOutputLow
        if (input > inHigh) return thisOutputHigh

        var switched = false
        if (thisOutputLow > thisOutputHigh) {
            val temp = thisOutputHigh
            thisOutputHigh = thisOutputLow
            thisOutputLow = temp
            switched = true
        }

        val endInput = interpolation.apply(inLow, inHigh,
            (input - inLow) / (inHigh - inLow)) // map input inside low / high to 0 till 1

        val scale = (thisOutputHigh - thisOutputLow) / (inHigh - inLow)
        val value = (endInput - inLow) * scale + thisOutputLow

        return if (switched) {
            thisOutputLow - value + thisOutputHigh
        } else
            value
    }
}