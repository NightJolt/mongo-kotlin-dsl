package com.glome.mongokotlindsl

import org.springframework.data.mongodb.core.aggregation.AggregationExpression
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators
import org.springframework.data.mongodb.core.query.Criteria
import kotlin.reflect.KProperty

object AggregationOperators {
    // region Eq
    infix fun String.eq(value: Any): Criteria {
        return Criteria.where(this).`is`(value)
    }

    infix fun KProperty<*>.eq(value: Any): Criteria {
        return Criteria.where(this.propertyToMongoField()).`is`(value)
    }

    infix fun AggregationExpression.eq(value: Any): AggregationExpression {
        return ComparisonOperators.Eq.valueOf(this).equalToValue(value)
    }
    // endregion

    // region Ne
    infix fun String.ne(value: Any): Criteria {
        return Criteria.where(this).ne(value)
    }

    infix fun KProperty<*>.ne(value: Any): Criteria {
        return Criteria.where(this.propertyToMongoField()).ne(value)
    }

    infix fun AggregationExpression.ne(value: Any): AggregationExpression {
        return ComparisonOperators.Ne.valueOf(this).notEqualToValue(value)
    }
    // endregion

    // region Gt
    infix fun String.gt(value: Any): Criteria {
        return Criteria.where(this).gt(value)
    }

    infix fun KProperty<*>.gt(value: Any): Criteria {
        return Criteria.where(this.propertyToMongoField()).gt(value)
    }

    infix fun AggregationExpression.gt(value: Any): AggregationExpression {
        return ComparisonOperators.Gt.valueOf(this).greaterThanValue(value)
    }
    // endregion

    // region Gte
    infix fun String.gte(value: Any): Criteria {
        return Criteria.where(this).gte(value)
    }

    infix fun KProperty<*>.gte(value: Any): Criteria {
        return Criteria.where(this.propertyToMongoField()).gte(value)
    }

    infix fun AggregationExpression.gte(value: Any): AggregationExpression {
        return ComparisonOperators.Gte.valueOf(this).greaterThanEqualToValue(value)
    }
    // endregion

    // region Lt
    infix fun String.lt(value: Any): Criteria {
        return Criteria.where(this).lt(value)
    }

    infix fun KProperty<*>.lt(value: Any): Criteria {
        return Criteria.where(this.propertyToMongoField()).lt(value)
    }

    infix fun AggregationExpression.lt(value: Any): AggregationExpression {
        return ComparisonOperators.Lt.valueOf(this).lessThanValue(value)
    }
    // endregion

    // region Lte
    infix fun String.lte(value: Any): Criteria {
        return Criteria.where(this).lte(value)
    }

    infix fun KProperty<*>.lte(value: Any): Criteria {
        return Criteria.where(this.propertyToMongoField()).lte(value)
    }

    infix fun AggregationExpression.lte(value: Any): AggregationExpression {
        return ComparisonOperators.Lte.valueOf(this).lessThanEqualToValue(value)
    }
    // endregion

    // region Mod
    infix fun String.mod(value: Number): AggregationExpression {
        return ArithmeticOperators.Mod.valueOf(this).mod(value)
    }

    infix fun KProperty<*>.mod(value: Number): AggregationExpression {
        return ArithmeticOperators.Mod.valueOf(this.propertyToMongoField()).mod(value)
    }

    infix fun AggregationExpression.mod(value: Number): AggregationExpression {
        return ArithmeticOperators.Mod.valueOf(this).mod(value)
    }

    infix fun String.mod(value: String): AggregationExpression {
        return ArithmeticOperators.Mod.valueOf(this).mod(value)
    }

    infix fun KProperty<*>.mod(value: String): AggregationExpression {
        return ArithmeticOperators.Mod.valueOf(this.propertyToMongoField()).mod(value)
    }

    infix fun AggregationExpression.mod(value: String): AggregationExpression {
        return ArithmeticOperators.Mod.valueOf(this).mod(value)
    }

    infix fun String.mod(value: KProperty<*>): AggregationExpression {
        return ArithmeticOperators.Mod.valueOf(this).mod(value.propertyToMongoField())
    }

    infix fun KProperty<*>.mod(value: KProperty<*>): AggregationExpression {
        return ArithmeticOperators.Mod.valueOf(this.propertyToMongoField()).mod(value.propertyToMongoField())
    }

    infix fun AggregationExpression.mod(value: KProperty<*>): AggregationExpression {
        return ArithmeticOperators.Mod.valueOf(this).mod(value.propertyToMongoField())
    }

    infix fun String.mod(value: AggregationExpression): AggregationExpression {
        return ArithmeticOperators.Mod.valueOf(this).mod(value)
    }

    infix fun KProperty<*>.mod(value: AggregationExpression): AggregationExpression {
        return ArithmeticOperators.Mod.valueOf(this.propertyToMongoField()).mod(value)
    }

    infix fun AggregationExpression.mod(value: AggregationExpression): AggregationExpression {
        return ArithmeticOperators.Mod.valueOf(this).mod(value)
    }
    // endregion

    // region Sub
    infix fun String.sub(value: Number): AggregationExpression {
        return ArithmeticOperators.Subtract.valueOf(this).subtract(value)
    }

    infix fun KProperty<*>.sub(value: Number): AggregationExpression {
        return ArithmeticOperators.Subtract.valueOf(this.propertyToMongoField()).subtract(value)
    }

    infix fun AggregationExpression.sub(value: Number): AggregationExpression {
        return ArithmeticOperators.Subtract.valueOf(this).subtract(value)
    }

    infix fun String.sub(value: String): AggregationExpression {
        return ArithmeticOperators.Subtract.valueOf(this).subtract(value)
    }

    infix fun KProperty<*>.sub(value: String): AggregationExpression {
        return ArithmeticOperators.Subtract.valueOf(this.propertyToMongoField()).subtract(value)
    }

    infix fun AggregationExpression.sub(value: String): AggregationExpression {
        return ArithmeticOperators.Subtract.valueOf(this).subtract(value)
    }

    infix fun String.sub(value: KProperty<*>): AggregationExpression {
        return ArithmeticOperators.Subtract.valueOf(this).subtract(value.propertyToMongoField())
    }

    infix fun KProperty<*>.sub(value: KProperty<*>): AggregationExpression {
        return ArithmeticOperators.Subtract.valueOf(this.propertyToMongoField()).subtract(value.propertyToMongoField())
    }

    infix fun AggregationExpression.sub(value: KProperty<*>): AggregationExpression {
        return ArithmeticOperators.Subtract.valueOf(this).subtract(value.propertyToMongoField())
    }

    infix fun String.sub(value: AggregationExpression): AggregationExpression {
        return ArithmeticOperators.Subtract.valueOf(this).subtract(value)
    }

    infix fun KProperty<*>.sub(value: AggregationExpression): AggregationExpression {
        return ArithmeticOperators.Subtract.valueOf(this.propertyToMongoField()).subtract(value)
    }

    infix fun AggregationExpression.sub(value: AggregationExpression): AggregationExpression {
        return ArithmeticOperators.Subtract.valueOf(this).subtract(value)
    }
    // endregion

    // region Add
    infix fun String.add(value: Number): AggregationExpression {
        return ArithmeticOperators.Add.valueOf(this).add(value)
    }

    infix fun KProperty<*>.add(value: Number): AggregationExpression {
        return ArithmeticOperators.Add.valueOf(this.propertyToMongoField()).add(value)
    }

    infix fun AggregationExpression.add(value: Number): AggregationExpression {
        return ArithmeticOperators.Add.valueOf(this).add(value)
    }

    infix fun String.add(value: String): AggregationExpression {
        return ArithmeticOperators.Add.valueOf(this).add(value)
    }

    infix fun KProperty<*>.add(value: String): AggregationExpression {
        return ArithmeticOperators.Add.valueOf(this.propertyToMongoField()).add(value)
    }

    infix fun AggregationExpression.add(value: String): AggregationExpression {
        return ArithmeticOperators.Add.valueOf(this).add(value)
    }

    infix fun String.add(value: KProperty<*>): AggregationExpression {
        return ArithmeticOperators.Add.valueOf(this).add(value.propertyToMongoField())
    }

    infix fun KProperty<*>.add(value: KProperty<*>): AggregationExpression {
        return ArithmeticOperators.Add.valueOf(this.propertyToMongoField()).add(value.propertyToMongoField())
    }

    infix fun AggregationExpression.add(value: KProperty<*>): AggregationExpression {
        return ArithmeticOperators.Add.valueOf(this).add(value.propertyToMongoField())
    }

    infix fun String.add(value: AggregationExpression): AggregationExpression {
        return ArithmeticOperators.Add.valueOf(this).add(value)
    }

    infix fun KProperty<*>.add(value: AggregationExpression): AggregationExpression {
        return ArithmeticOperators.Add.valueOf(this.propertyToMongoField()).add(value)
    }

    infix fun AggregationExpression.add(value: AggregationExpression): AggregationExpression {
        return ArithmeticOperators.Add.valueOf(this).add(value)
    }
    // endregion
}