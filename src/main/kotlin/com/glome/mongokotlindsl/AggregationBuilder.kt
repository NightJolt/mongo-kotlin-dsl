package com.glome.mongokotlindsl

import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.AggregationOperation
import org.springframework.data.mongodb.core.aggregation.LookupOperation

class AggregationBuilder {
    val operationList = mutableListOf<AggregationOperation>()

    operator fun AggregationOperation.unaryPlus() {
        operationList.add(this)
    }

    inline fun match(block: CriteriaListBuilder.() -> Unit) {
        val builder = CriteriaListBuilder()
        builder.block()

        operationList.add(Aggregation.match(builder.criteriaList.first()))
    }

    fun limit(limit: Long) {
        operationList.add(Aggregation.limit(limit))
    }

    fun lookup(block: LookupBuilder.() -> Unit) {
        val builder = LookupBuilder()
        builder.block()

        val operation = LookupOperation.newLookup()
            .from(builder.from)

        if (builder.localField != null) {
            operation
                .localField(builder.localField!!)
                .foreignField(builder.foreignField)
        }

        if (builder.vars.isNotEmpty()) {
            operation.let(*builder.vars)
        }

        if (builder.operations.isNotEmpty()) {
            operation.pipeline(*builder.operations)
        }

        operationList.add(operation.`as`(builder.alias!!))

        if (builder.flatten != null) {
            operationList.add(
                Aggregation.unwind(
                    builder.alias!!,
                    builder.flatten!!
                )
            )
        }
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

    fun project(block: ProjectListBuilder.() -> Unit) {
        val builder = ProjectListBuilder()
        builder.block()

        var projectBuilder = Aggregation.project()

        for (include in builder.includes) {
            projectBuilder = projectBuilder.andInclude(include)
        }

        if (builder.excludeId) {
            projectBuilder = projectBuilder.andExclude("_id")
        }

        for ((source, alias) in builder.aliases) {
            projectBuilder = projectBuilder.and(alias).`as`(source)
        }

        operationList.add(projectBuilder)
    }

    fun addFields(block: AddFieldsBuilder.() -> Unit) {
        val builder = AddFieldsBuilder()
        builder.block()

        val addFieldsOperation = Aggregation.addFields()

        for ((field, expression) in builder.fields) {
            addFieldsOperation.addField(field).withValue(expression)
        }

        operationList.add(addFieldsOperation.build())
    }

    fun group(block: GroupBuilder.() -> Unit) {
        val builder = GroupBuilder()
        builder.block()

        var groupOperation = Aggregation.group(builder.fieldToGroup)

        for ((lf, rf) in builder.pushFields) {
            groupOperation = groupOperation.push(rf).`as`(lf)
        }

        for ((lf, rf) in builder.firstFields) {
            groupOperation = groupOperation.first(rf).`as`(lf)
        }

        operationList.add(groupOperation)
    }
}