package com.kotlinorm.api

import com.kotlinorm.orm.delete.delete
import com.kotlinorm.orm.insert.insert
import com.kotlinorm.orm.select.select
import com.kotlinorm.orm.update.update
import com.kotlinorm.pojo.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:5173"], allowCredentials = "false")
@RestController
@RequestMapping("/api/users")
class UserRestController {

    @GetMapping
    fun list(): List<User> = User().select().toList()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<User> {
        val one = User().select().where { it.id == id }.firstOrNull()
        return ResponseEntity.ok(one)
    }

    data class SaveUserReq(
        val username: String,
        val password: String,
        val enabled: Boolean? = true
    )

    @PostMapping
    fun create(@RequestBody body: SaveUserReq): ResponseEntity<User> {
        val u = User(
            username = body.username,
            password = body.password,
            enabled = body.enabled != false
        )
        val created = u.insert().withId().execute().lastInsertId

        return ResponseEntity.status(HttpStatus.CREATED).body(
            User(created!!.toInt()).select().where().first()
        )
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody body: SaveUserReq): ResponseEntity<Void> {
        User().select().where { it.id == id }.firstOrNull() ?: return ResponseEntity.notFound().build()
        val enabled = body.enabled != false
        val u = User(id = id)
        u.update().set {
            it.username = body.username
            it.password = body.password
            it.enabled = enabled
        }.by { it.id }.execute()
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        User(id = id).delete().logic(false).execute()
        return ResponseEntity.noContent().build()
    }
}
