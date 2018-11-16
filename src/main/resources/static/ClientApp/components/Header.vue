<template>
<div>
    <div v-if="name != ''">
        <h2>Hi, {{ name }}!</h2>
    </div>
    <nav class="nav">
          <ul>
              <li><a href="/">главная</a></li>
              <li><a href="/admin">админская</a></li>
              <li v-if="name != ''">
                  <form :action="action" method="POST">
                      <input type="submit" value="Выйти"/>
                  </form>
              </li>
          </ul>
      </nav>
</div>
</template>
<script src="./Header.ts" lang="ts"></script>