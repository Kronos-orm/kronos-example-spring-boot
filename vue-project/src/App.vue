<template>
  <main class="container">
    <h1>Kronos User CRUD (Vue)</h1>
    <UserCrud />
  </main>
</template>

<script setup>
import UserCrud from './components/UserCrud.vue'
</script>

<style>
body { font-family: system-ui, -apple-system, Segoe UI, Roboto, Ubuntu, Cantarell, Noto Sans, Helvetica, Arial, "Apple Color Emoji", "Segoe UI Emoji"; }
.container { max-width: 900px; margin: 24px auto; padding: 0 16px; }
.table { width: 100%; border-collapse: collapse; margin-top: 12px; }
.table th, .table td { border: 1px solid #ddd; padding: 8px; }
.table th { background: #f5f5f5; text-align: left; }
.btn { padding: 6px 12px; margin-right: 6px; cursor: pointer; border: 1px solid #ccc; background: #fff; }
.btn.primary { background: #42b883; color: #fff; border-color: #42b883; }
.btn.danger { background: #e53935; color: #fff; border-color: #e53935; }
input[type="text"], input[type="password"], input[type="checkbox"] { margin: 4px; }
.form-row { margin: 8px 0; }
</style>
