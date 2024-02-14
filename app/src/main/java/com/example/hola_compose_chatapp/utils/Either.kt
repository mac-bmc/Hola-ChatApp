package com.example.hola_compose_chatapp.utils

sealed class Either<T> {
    class Success<T>(val data:T):Either<T>()
    class Failed<T>(val msg:String):Either<T>()
}