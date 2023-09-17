package com.dhook.game.General

class AnimationModule {
    enum class SpriteDirection(val angleDegrees: Float) {
        UP(90f),
        DOWN(270f),
        RIGHT(0f),
        LEFT(180f),
        UPRIGHT(45f),
        UPLEFT(135f),
        DOWNRIGHT(315f),
        DOWNLEFT(225f)
    }
}