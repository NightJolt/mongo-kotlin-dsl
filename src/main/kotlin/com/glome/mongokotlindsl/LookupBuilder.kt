package com.glome.mongokotlindsl

import org.springframework.data.mongodb.core.aggregation.AggregationOperation
import org.springframework.data.mongodb.core.aggregation.VariableOperators.Let.ExpressionVariable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class LookupBuilder {
    lateinit var from: String
    var localField: String? = null
    var foreignField: String = "_id"
    var alias: String? = null
    var vars: Array<ExpressionVariable> = emptyArray()
    var operations: Array<AggregationOperation> = emptyArray()
    var flatten: Boolean? = null

    fun from(from: String) {
        this.from = from
        this.alias = this.alias ?: this.from
    }

    fun from(from: KClass<*>) {
        this.from = getCollectionName(from)
        this.alias = this.alias ?: this.from
    }

    fun localField(localField: String) {
        this.localField = localField
    }

    fun localField(localField: KProperty<*>) {
        this.localField = localField.propertyToMongoField()
    }

    fun foreignField(foreignField: String) {
        this.foreignField = foreignField
    }

    fun foreignField(foreignField: KProperty<*>) {
        this.foreignField = foreignField.propertyToMongoField()
    }

    fun alias(alias: String) {
        this.alias = alias
    }

    fun alias(alias: KProperty<*>) {
        this.alias = alias.propertyToMongoField()
    }

    fun let(block: VarListBuilder.() -> Unit) {
        val builder = VarListBuilder()
        builder.block()
        vars = builder.vars.toTypedArray()
    }

    fun pipeline(block: AggregationBuilder.() -> Unit) {
        val builder = AggregationBuilder()
        builder.block()
        operations = builder.operationList.toTypedArray()
    }

    fun flatten(preserveNullAndEmptyArrays: Boolean = false) {
        this.flatten = preserveNullAndEmptyArrays
    }
}