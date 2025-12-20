package com.glome.mongokotlindsl

import kotlin.reflect.KProperty

class ProjectListBuilder {
    var excludeId = false
    val includes = mutableListOf<String>()
    val aliases = mutableListOf<Pair<String, String>>()

    infix fun KProperty<*>.inc(include: Boolean) {
        if (!include && this.name == "id") excludeId = true

        includes.add(this.propertyToMongoField())
    }

    infix fun KProperty<*>.ass(alias: KProperty<*>) {
        aliases.add(this.propertyToMongoField() to alias.propertyToMongoField())
    }

    infix fun KProperty<*>.ass(alias: String) {
        aliases.add(this.propertyToMongoField() to alias)
    }

    infix fun String.ass(alias: KProperty<*>) {
        aliases.add(this to alias.propertyToMongoField())
    }

    infix fun String.ass(alias: String) {
        aliases.add(this to alias)
    }
}