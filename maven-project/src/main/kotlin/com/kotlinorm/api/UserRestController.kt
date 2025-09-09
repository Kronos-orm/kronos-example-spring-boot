package com.kotlinorm.api

import com.kotlinorm.orm.delete.delete
import com.kotlinorm.orm.insert.insert
import com.kotlinorm.orm.select.select
import com.kotlinorm.orm.update.update
import com.kotlinorm.pojo.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["http://localhost:5173"], allowCredentials = "false")
@RestController
@RequestMapping("/api/users")
class UserRestController {

    @GetMapping
    fun list(): List<User> = User().select().queryList()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<User> {
        val one = User().select().where { it.id == id }.queryOne()
        return if (one != null) ResponseEntity.ok(one) else ResponseEntity.notFound().build()
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
            enabled = body.enabled ?: true
        )
        u.insert().execute()
        // Return the created entity (with generated id)
        val created = User().select().where { it.username == body.username }.queryList().maxByOrNull { it.id ?: 0 }
        return ResponseEntity.status(HttpStatus.CREATED).body(created ?: u)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody body: SaveUserReq): ResponseEntity<Void> {
        val exists = User().select().where { it.id == id }.queryOne() ?: return ResponseEntity.notFound().build()
        val u = User(id = id)
        u.update().set {
            it.username = body.username
            it.password = body.password
            it.enabled = body.enabled ?: true
        }.by { it.id }.execute()
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        User(id = id).delete().execute()
        return ResponseEntity.noContent().build()
    }
}
