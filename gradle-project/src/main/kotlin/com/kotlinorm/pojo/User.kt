package com.kotlinorm.pojo

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kotlinorm.annotations.*
import com.kotlinorm.interfaces.KPojo
import java.time.LocalDateTime

@TableIndex(
    "idx_username",
    ["username"],
    "UNIQUE",
    "BTREE"
)

data class User(

    @PrimaryKey(identity = true)
    var id: Int? = null, //id

    @get:JvmName("name")
    @NonNull
    var username: String? = null, //用户名

    @get:JvmName("pwd")
    @NonNull
    var password: String? = null, //pwd

    @Default("1") var enabled: Boolean? = null, //是否启用

    @LogicDelete
    @JsonIgnore
    @Default("0") var deleted: Boolean? = null, //删除标记

    @CreateTime
    var createTime: LocalDateTime? = null, //创建时间

    @UpdateTime
    var updateTime: LocalDateTime? = null, //更新时间

): KPojo
