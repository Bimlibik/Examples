package com.foxy.recyclerview.simple_rv

import java.util.*

data class Tree(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String)