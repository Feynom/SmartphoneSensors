package com.example.testapplication

class Vector3(val x: Float, val y: Float, val z: Float) {
    override fun toString(): String {
        return "x: %.2f, y: %.2f, z: %.2f".format(x, y, z)
    }
}

