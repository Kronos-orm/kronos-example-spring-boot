<template>
  <section>
    <div class="form-row">
      <label>
        Username:
        <input v-model="form.username" type="text" />
      </label>
    </div>
    <div class="form-row">
      <label>
        Password:
        <input v-model="form.password" type="password" />
      </label>
    </div>
    <div class="form-row">
      <label>
        <input v-model="form.enabled" type="checkbox" /> Enabled
      </label>
    </div>
    <div class="form-row">
      <button class="btn primary" @click="save">{{ form.id ? 'Update' : 'Create' }}</button>
      <button class="btn" @click="reset">Reset</button>
    </div>

    <table class="table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Username</th>
          <th>Enabled</th>
          <th>Create Time</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="u in users" :key="u.id">
          <td>{{ u.id }}</td>
          <td>{{ u.username }}</td>
          <td>{{ u.enabled ? 'Yes' : 'No' }}</td>
          <td>{{ u.createTime ?? '-' }}</td>
          <td>
            <button class="btn" @click="edit(u)">Edit</button>
            <button class="btn danger" @click="remove(u)">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </section>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue'
import { listUsers, createUser, updateUser, deleteUser } from '../api'

const users = ref([])
const form = reactive({ id: null, username: '', password: '', enabled: true })

function load() {
  listUsers().then(d => users.value = d)
}

function reset() {
  form.id = null
  form.username = ''
  form.password = ''
  form.enabled = true
}

function edit(u) {
  form.id = u.id
  form.username = u.username
  form.password = ''
  form.enabled = !!u.enabled
}

async function save() {
  const payload = { username: form.username, password: form.password, enabled: form.enabled }
  if (!form.username || !form.password) return alert('Username and password are required')
  if (form.id) {
    await updateUser(form.id, payload)
  } else {
    await createUser(payload)
  }
  reset()
  load()
}

async function remove(u) {
  if (!confirm(`Delete user #${u.id}?`)) return
  await deleteUser(u.id)
  load()
}

onMounted(load)
</script>
