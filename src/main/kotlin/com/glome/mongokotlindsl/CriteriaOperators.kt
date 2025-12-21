package com.glome.mongokotlindsl

import org.springframework.data.mongodb.core.query.Criteria
import kotlin.reflect.KProperty

object CriteriaOperators {
    // region Eq
    infix fun String.eq(value: Any): Criteria {
        return Criteria.where(this).`is`(value)
    }

    infix fun KProperty<*>.eq(value: Any): Criteria {
        return Criteria.where(this.propertyToMongoField()).`is`(value)
    }
    // endregion

    // region Ne
    infix fun String.ne(value: Any): Criteria {
        return Criteria.where(this).ne(value)
    }

    infix fun KProperty<*>.ne(value: Any): Criteria {
        return Criteria.where(this.propertyToMongoField()).ne(value)
    }
    // endregion

    // region Gt
    infix fun String.gt(value: Any): Criteria {
        return Criteria.where(this).gt(value)
    }

    infix fun KProperty<*>.gt(value: Any): Criteria {
        return Criteria.where(this.propertyToMongoField()).gt(value)
    }
    // endregion

    // region Gte
    infix fun String.gte(value: Any): Criteria {
        return Criteria.where(this).gte(value)
    }

    infix fun KProperty<*>.gte(value: Any): Criteria {
        return Criteria.where(this.propertyToMongoField()).gte(value)
    }
    // endregion

    // region Lt
    infix fun String.lt(value: Any): Criteria {
        return Criteria.where(this).lt(value)
    }

    infix fun KProperty<*>.lt(value: Any): Criteria {
        return Criteria.where(this.propertyToMongoField()).lt(value)
    }
    // endregion

    // region Lte
    infix fun String.lte(value: Any): Criteria {
        return Criteria.where(this).lte(value)
    }

    infix fun KProperty<*>.lte(value: Any): Criteria {
        return Criteria.where(this.propertyToMongoField()).lte(value)
    }
    // endregion

    // region In
    infix fun String.`in`(value: List<Any>): Criteria {
        return Criteria.where(this).`in`(value)
    }

    infix fun KProperty<*>.`in`(value: List<Any>): Criteria {
        return Criteria.where(this.propertyToMongoField()).`in`(value)
    }
    // endregion
}