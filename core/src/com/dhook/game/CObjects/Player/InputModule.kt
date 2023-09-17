package com.dhook.game.CObjects.Player

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.dhook.game.CEngine.CObjectDinamico
import com.dhook.game.General.Utils

/** Un módulo de input para un objeto dinámico: le seteas los inputs de movimiento (joystick o teclas pulsadas en cada dirección)
 * y este módulo calcula el porcentaje. Los detalles de movimiento se especifican en una subclase (si es teclado, mando, etc) */
abstract class InputModule(val objectoDinamico: CObjectDinamico) {
    var rawMovingAngle = 0f // ángulo raw = ángulo real, sin procesamientos extra
    var smoothedMovingAngle = 0f // ángulo smoothed = ángulo que se procesa con una animación de acercamiento al ángulo real,
                                    // para que los cambios pequeños de dirección no sean instantáneos
    var movingPercent = 0f

    open fun update(delta: Float) {

    }
}

class InputModuleKeyboard(objectoDinamico: CObjectDinamico) : InputModule(objectoDinamico) {
    // las 4 direcciones que se pueden pulsar en un teclado
    enum class InputDirection {
        UP, DOWN, LEFT, RIGHT;

        val opposite: InputDirection
            get() {
                return when (this) {
                    DOWN -> UP
                    LEFT -> RIGHT
                    RIGHT -> LEFT
                    UP -> DOWN
                }
            }

        fun toDegrees(): Int {
            return when (this) {
                UP -> 90
                RIGHT -> 0
                LEFT -> 180
                DOWN -> 270
            }
        }
    }

    var inputDown: Boolean = false
    var inputUp: Boolean = false
    var inputLeft: Boolean = false
    var inputRight: Boolean = false

    private var keyboardMovingDirection: InputDirection? = InputDirection.DOWN
    private var secondaryFacingDirection: InputDirection? = null
    private var changedMovingDirection = false

    fun resetInputBooleans() {
        inputUp = false
        inputDown = false
        inputRight = false
        inputLeft = false
    }

    fun anyMovementIsTrue(): Boolean {
        return inputDown || inputUp || inputLeft || inputRight
    }

    private val tmpVector = Vector2()

    override fun update(delta: Float) {
        super.update(delta)
        secondaryFacingDirection = null
        changedMovingDirection = false
        tmpVector.set(0f, 0f)

        if (anyMovementIsTrue()) keyboardMovingDirection = null

        if (anyMovementIsTrue() && (keyboardMovingDirection == InputDirection.UP && !inputUp
                    || keyboardMovingDirection == InputDirection.DOWN && !inputDown
                    || keyboardMovingDirection == InputDirection.LEFT && !inputLeft
                    || keyboardMovingDirection == InputDirection.RIGHT && !inputRight)) {
            keyboardMovingDirection = null
        }

        val oldMovingDirection = keyboardMovingDirection

        if (keyboardMovingDirection != InputDirection.RIGHT && keyboardMovingDirection != InputDirection.LEFT && inputUp) {
            keyboardMovingDirection = InputDirection.UP
            tmpVector.y += objectoDinamico.velocidadMovimiento
        } else if (keyboardMovingDirection != InputDirection.RIGHT && keyboardMovingDirection != InputDirection.LEFT && inputDown) {
            keyboardMovingDirection = InputDirection.DOWN
            tmpVector.y -= objectoDinamico.velocidadMovimiento
        } else if (keyboardMovingDirection != InputDirection.UP && keyboardMovingDirection != InputDirection.DOWN && inputLeft) {
            keyboardMovingDirection = InputDirection.LEFT
            tmpVector.x -= objectoDinamico.velocidadMovimiento
        } else if (keyboardMovingDirection != InputDirection.UP && keyboardMovingDirection != InputDirection.DOWN && inputRight) {
            keyboardMovingDirection = InputDirection.RIGHT
            tmpVector.x += objectoDinamico.velocidadMovimiento
        }

        if (keyboardMovingDirection == InputDirection.UP || keyboardMovingDirection == InputDirection.DOWN) {
            if (inputLeft) {
                secondaryFacingDirection = InputDirection.LEFT
            } else if (inputRight) {
                secondaryFacingDirection = InputDirection.RIGHT
            }
        }

        if (keyboardMovingDirection == InputDirection.LEFT || keyboardMovingDirection == InputDirection.RIGHT) {
            if (inputDown) {
                secondaryFacingDirection = InputDirection.DOWN
            } else if (inputUp) {
                secondaryFacingDirection = InputDirection.UP
            }
        }

        if (oldMovingDirection != keyboardMovingDirection) {
            changedMovingDirection = true
        }

        // diagonal movement
        if (secondaryFacingDirection != null) {
            val reduction = MathUtils.sinDeg(45f)

            if (secondaryFacingDirection == InputDirection.LEFT || secondaryFacingDirection == InputDirection.RIGHT) {
                tmpVector.y *= reduction
                tmpVector.x = if (secondaryFacingDirection == InputDirection.LEFT) -objectoDinamico.velocidadMovimiento * reduction
                else objectoDinamico.velocidadMovimiento * reduction
            } else {
                tmpVector.x *= reduction
                tmpVector.y = if (secondaryFacingDirection == InputDirection.DOWN) -objectoDinamico.velocidadMovimiento * reduction
                else objectoDinamico.velocidadMovimiento * reduction
            }
        }

        movingPercent = 0f

        if (Math.abs(tmpVector.x) >= 0.03f || Math.abs(tmpVector.y) >= 0.03f) {
            movingPercent = 1f
            rawMovingAngle = Math.toDegrees(Math.atan2(tmpVector.y.toDouble(), tmpVector.x.toDouble())).toFloat()
        }

        // Aproximamos el ángulo "smoothed" para que se acerque al ángulo real
        if (movingPercent > 0f) {
            val diferencia = Utils.getAngleDifferenceNotSigned(smoothedMovingAngle, rawMovingAngle)
            if (diferencia > 60f) {
                // la diferencia es muy grande: no animes el ángulo
                smoothedMovingAngle = rawMovingAngle
            } else {
                val velocidadDeAproximacion = Utils.map(diferencia, 45f, 60f, 0.2f, 0.99f)
                smoothedMovingAngle =
                    MathUtils.lerpAngleDeg(smoothedMovingAngle, rawMovingAngle, velocidadDeAproximacion)
            }
        }
    }
}