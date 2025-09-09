package com.kotlinorm.pojo

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kotlinorm.annotations.*
import com.kotlinorm.enums.CascadeDeleteAction
import com.kotlinorm.enums.Mysql
import com.kotlinorm.interfaces.KPojo
import java.time.LocalDateTime

@TableIndex(
    "idx_username",
    ["username"],
    Mysql.KIndexType.UNIQUE,
    Mysql.KIndexMethod.BTREE
)

data class User(

    @PrimaryKey(identity = true)
    var id: Int? = null, //id

    @get:JvmName("name")
    @Necessary
    var username: String? = null, //用户名

    @get:JvmName("pwd")
    @Necessary
    var password: String? = null, //pwd

    @Default("1") var enabled: Boolean? = null, //是否启用

    @LogicDelete
    @JsonIgnore
    @Default("0") var deleted: Boolean? = null, //删除标记

    @CreateTime
    var createTime: LocalDateTime? = null, //创建时间

    @UpdateTime
    @JsonIgnore
    var updateTime: LocalDateTime? = null, //更新时间

): KPojo
