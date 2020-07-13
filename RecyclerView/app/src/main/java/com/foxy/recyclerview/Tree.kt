package com.foxy.recyclerview

import java.util.*

data class Tree(
    val id: String = UUID.randomUUID().toString(),
    val clicked: Boolean = false,
    val name: String,
    val description: String)