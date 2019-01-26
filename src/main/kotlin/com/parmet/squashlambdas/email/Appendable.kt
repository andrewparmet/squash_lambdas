package com.parmet.squashlambdas.email

interface Appendable<T> : Iterable<T> {
    fun append(toAppend: T): Appendable<T>

    fun appendAll(toAppends: Appendable<T>) = apply { toAppends.forEach { append(it) } }
}
