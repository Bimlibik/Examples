package com.foxy.recyclerview

import java.util.*

data class Tree(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String)