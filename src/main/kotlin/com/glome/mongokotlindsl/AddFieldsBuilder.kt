package com.glome.mongokotlindsl

import kotlin.reflect.KProperty

class AddFieldsBuilder {
    val fields = mutableListOf<Pair<String, Any>>()

    infix fun String.ass(value: Any) {
        fields.add(this to value)
    }

    infix fun KProperty<*>.ass(value: Any) {
        fields.add(this.propertyToMongoField() to value)
    }
}