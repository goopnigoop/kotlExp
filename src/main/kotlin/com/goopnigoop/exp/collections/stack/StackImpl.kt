package com.goopnigoop.exp.collections.stack

class StackImpl<V>(size: Int) : Stack<V>{
    operator fun invoke(size: Int){
        if (size < 0) {
            throw IllegalArgumentException("Stack size should be more than 0")
        }
    }
}