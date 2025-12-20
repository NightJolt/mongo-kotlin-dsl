package com.glome.mongokotlindsl

class UnwindBuilder {
    lateinit var field: String
    var preserveNullAndEmptyArrays: Boolean = false

    fun field(field: String) {
        this.field = field
    }

    fun preserveNullAndEmptyArrays(preserve: Boolean) {
        this.preserveNullAndEmptyArrays = preserve
    }
}