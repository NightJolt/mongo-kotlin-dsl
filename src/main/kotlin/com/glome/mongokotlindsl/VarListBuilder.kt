package com.glome.mongokotlindsl

import org.springframework.data.mongodb.core.aggregation.AggregationExpression
import org.springframework.data.mongodb.core.aggregation.VariableOperators.Let.ExpressionVariable
import kotlin.reflect.KProperty

class VarListBuilder {
    val vars = mutableListOf<ExpressionVariable>()

    infix fun String.ass(field: String) {
        vars.add(ExpressionVariable.newVariable(this).forField(field))
    }

    infix fun String.ass(field: KProperty<*>) {
        vars.add(ExpressionVariable.newVariable(this).forField(field.propertyToMongoField()))
    }

    infix fun String.ass(expr: AggregationExpression) {
        vars.add(ExpressionVariable.newVariable(this).forExpression(expr))
    }

    infix fun KProperty<*>.ass(field: String) {
        vars.add(ExpressionVariable.newVariable(this.propertyToMongoField()).forField(field))
    }

    infix fun KProperty<*>.ass(field: KProperty<*>) {
        vars.add(ExpressionVariable.newVariable(this.propertyToMongoField()).forField(field.propertyToMongoField()))
    }

    infix fun KProperty<*>.ass(expr: AggregationExpression) {
        vars.add(ExpressionVariable.newVariable(this.propertyToMongoField()).forExpression(expr))
    }
}