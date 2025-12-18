package com.glome.mongokotlindsl

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.*
import org.springframework.data.mongodb.core.mapping.Document
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

fun size(field: String): AggregationExpression = ArrayOperators.Size.lengthOfArray(field)
fun size(field: KProperty<*>): AggregationExpression = ArrayOperators.Size.lengthOfArray(field.name)
fun obj(value: String): ObjectId = ObjectId(value)
fun field(value: String): String = "$$value"
fun field(value: KProperty<*>): String = "$${value.name}"
fun varref(value: String): String = "$$$value"
fun varref(value: KProperty<*>): String = "$$${value.name}"

fun getCollectionName(clazz: KClass<*>): String {
    val document = clazz.java.getAnnotation(Document::class.java)
    return document?.collection?.takeIf { it.isNotEmpty() }
        ?: clazz.simpleName?.lowercase()
        ?: "collectionNotFound"
}

fun KProperty<*>.propertyToMongoField(): String {
    return this.name.replace(Regex("([a-z])([A-Z]+)"), "$1_$2").lowercase()
        .let { if (this.name == "id") "_$it" else it }
}

inline fun <reified T, reified O> MongoTemplate.run(
    aggregation: Aggregation
): AggregationResults<O> =
    aggregate(
        aggregation,
        getCollectionName(T::class),
        O::class.java,
    )

inline fun aggregation(block: OperationListBuilder.() -> Unit): Aggregation {
    val builder = OperationListBuilder()
    builder.block()
    return Aggregation.newAggregation(builder.operationList)
}