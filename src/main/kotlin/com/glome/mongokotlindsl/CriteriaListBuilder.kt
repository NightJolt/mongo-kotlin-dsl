package com.glome.mongokotlindsl

import org.springframework.data.mongodb.core.aggregation.AggregationExpression
import org.springframework.data.mongodb.core.query.Criteria

class CriteriaListBuilder {
    val criteriaList = mutableListOf<Criteria>()

    inline fun where(block: AggregationOperators.() -> Criteria) {
        criteriaList.add(AggregationOperators.block())
    }

    inline fun and(block: CriteriaListBuilder.() -> Unit) {
        val builder = CriteriaListBuilder()
        builder.block()
        criteriaList.add(Criteria().andOperator(*builder.criteriaList.toTypedArray()))
    }

    inline fun or(block: CriteriaListBuilder.() -> Unit) {
        val builder = CriteriaListBuilder()
        builder.block()
        criteriaList.add(Criteria().orOperator(*builder.criteriaList.toTypedArray()))
    }

    inline fun expr(block: AggregationOperators.() -> AggregationExpression) {
        criteriaList.add(Criteria.expr(AggregationOperators.block()))
    }
}