package com.glome.mongokotlindsl

import kotlin.reflect.KProperty

class GroupBuilder {
    lateinit var fieldToGroup: String
    val pushFields = mutableListOf<Pair<String, String>>()
    val firstFields = mutableListOf<Pair<String, String>>()

    fun field(field: String) {
        fieldToGroup = field
    }

    fun field(field: KProperty<*>) {
        fieldToGroup = field.propertyToMongoField()
    }

    infix fun String.push(value: String) {
        pushFields.add(this to value)
    }

    infix fun KProperty<*>.push(value: String) {
        pushFields.add(this.propertyToMongoField() to value)
    }

    infix fun String.push(value: KProperty<*>) {
        pushFields.add(this to value.propertyToMongoField())
    }

    infix fun KProperty<*>.push(value: KProperty<*>) {
        pushFields.add(this.propertyToMongoField() to value.propertyToMongoField())
    }

    infix fun String.first(value: String) {
        firstFields.add(this to value)
    }

    infix fun KProperty<*>.first(value: String) {
        firstFields.add(this.propertyToMongoField() to value)
    }

    infix fun String.first(value: KProperty<*>) {
        firstFields.add(this to value.propertyToMongoField())
    }

    infix fun KProperty<*>.first(value: KProperty<*>) {
        firstFields.add(this.propertyToMongoField() to value.propertyToMongoField())
    }
}