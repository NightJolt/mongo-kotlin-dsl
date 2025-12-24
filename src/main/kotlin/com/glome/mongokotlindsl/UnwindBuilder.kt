package com.glome.mongokotlindsl

import kotlin.reflect.KProperty

class UnwindBuilder {
    lateinit var field: String
    var preserveNullAndEmptyArrays: Boolean = false

    fun field(field: String) {
        this.field = field
    }

    fun field(field: KProperty<*>) {
        this.field = field.propertyToMongoField()
    }

    fun preserveNullAndEmptyArrays(preserve: Boolean) {
        this.preserveNullAndEmptyArrays = preserve
    }
}