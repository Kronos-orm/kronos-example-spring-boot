package com.kotlinorm.kronosSpringDemo.pojos

import com.kotlinorm.annotations.PrimaryKey
import com.kotlinorm.annotations.Table
import com.kotlinorm.interfaces.KPojo

@Table("tb_user")
data class User(
    @PrimaryKey(identity = true)
    val id: Int? = null,
    val name: String? = null, // name for user
    val age: Int? = null,
) : KPojo