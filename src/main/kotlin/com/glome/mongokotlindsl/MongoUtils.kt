package com.glome.mongokotlindsl

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.*
import org.springframework.data.mongodb.core.mapping.Document
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

fun size(field: String): AggregationExpression = ArrayOperators.Size.lengthOfArray(field)
fun size(field: KProperty<*>): AggregationExpression = ArrayOperators.Size.lengthOfArray(field.propertyToMongoField())
fun obj(value: String): ObjectId = ObjectId(value)
fun field(value: String): String = "$$value"
fun field(value: KProperty<*>): String = "$${value.propertyToMongoField()}"
fun varref(value: String): String = "$$$value"
fun varref(value: KProperty<*>): String = "$$${value.propertyToMongoField()}"

infix fun String.dot(subfield: String): String = "$this.$subfield"
infix fun String.dot(subfield: KProperty<*>): String = "$this.${subfield.propertyToMongoField()}"
infix fun KProperty<*>.dot(subfield: String): String = "${this.propertyToMongoField()}.$subfield"
infix fun KProperty<*>.dot(subfield: KProperty<*>): String = "${this.propertyToMongoField()}.${subfield.propertyToMongoField()}"

fun getCollectionName(clazz: KClass<*>): String {
    val document = clazz.java.getAnnotation(Document::class.java)
    return document?.collection?.takeIf { it.isNotEmpty() }
        ?: clazz.simpleName?.lowercase()
        ?: "collectionNotFound"
}

fun KProperty<*>.propertyToMongoField(): String {
    if (this.name == "id") return "_id"

    return this.name.replace(Regex("([a-z])([A-Z]+)"), "$1_$2").lowercase()
}

class AggregationOperations(val operationList: List<AggregationOperation>) {
    operator fun plus(operations: AggregationOperations): AggregationOperations {
        return AggregationOperations(this.operationList + operations.operationList)
    }
}

inline fun <reified T, reified O> MongoTemplate.run(
    aggregation: AggregationOperations
): AggregationResults<O> =
    aggregate(
        Aggregation.newAggregation(aggregation.operationList),
        getCollectionName(T::class),
        O::class.java,
    )

inline fun aggregation(block: AggregationBuilder.() -> Unit): AggregationOperations {
    val builder = AggregationBuilder()
    builder.block()
    return AggregationOperations(builder.operationList)
}