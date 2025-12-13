package com.glome.mongokotlindsl

import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.AggregationOperation
import org.springframework.data.mongodb.core.aggregation.LookupOperation
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class LookupBuilder {
    lateinit var from: String
    lateinit var localField: String
    lateinit var foreignField: String
    lateinit var `as`: String
    var operations: Array<AggregationOperation> = emptyArray()

    fun from(from: KClass<*>) {
        this.from = getCollectionName(from)
    }

    fun localField(localField: KProperty<*>) {
        this.localField = localField.propertyToMongoField()
    }

    fun foreignField(foreignField: KProperty<*>) {
        this.foreignField = foreignField.propertyToMongoField()
    }

    fun `as`(`as`: String) {
        this.`as` = `as`
    }

    fun pipeline(block: OperationListBuilder.() -> Unit) {
        val builder = OperationListBuilder()
        builder.block()
        operations = builder.operationList.toTypedArray()
    }
}

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

class OperationListBuilder {
    val operationList = mutableListOf<AggregationOperation>()

    inline fun match(block: CriteriaListBuilder.() -> Unit) {
        val builder = CriteriaListBuilder()
        builder.block()

        operationList.add(Aggregation.match(builder.criteriaList.first()))
    }

    fun lookup(block: LookupBuilder.() -> Unit) {
        val builder = LookupBuilder()
        builder.block()

        val operation = LookupOperation.newLookup()
            .from(builder.from)
            .localField(builder.localField)
            .foreignField(builder.foreignField)
            .pipeline(*builder.operations)
            .`as`(builder.`as`)

        operationList.add(operation)
    }

    fun unwind(block: UnwindBuilder.() -> Unit) {
        val builder = UnwindBuilder()
        builder.block()

        operationList.add(
            Aggregation.unwind(
                builder.field,
                builder.preserveNullAndEmptyArrays
            )
        )
    }
}