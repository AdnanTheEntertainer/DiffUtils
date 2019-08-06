package com.example.diffutils


data class Model(var id: Int,var name: String, var price: Int) : Comparable<Model>, Cloneable {
    override fun compareTo(other: Model): Int {
        val compare = other as Model

        return if (compare.id == this.id && compare.name.equals(this.name) && compare.price == this.price) {
            0
        } else 1
    }

    public override fun clone(): Any {
        val clone: Model
        try {
            clone = super.clone() as Model

        } catch (e: CloneNotSupportedException) {
            throw RuntimeException(e) //should not happen
        }


        return clone    }

}