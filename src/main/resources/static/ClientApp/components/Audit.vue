<template>
<div>
  <h2>AUDIT</h2>


   <input placeholder="найти по имени" v-model="username" type="text">
   <input type="submit" @click="get_all_by_username(username)" value="OK"/>


  <table>
      <thead>
      <tr>
        <th v-for="col in columns">{{col}}</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="audit in get_rows()">
          <td v-for="col in columns">{{audit[col]}}</td>
      </tr>
      </tbody>
  </table>
  <div class="pagination">
      <div class="number"
            v-for="i in num_pages()"
            v-bind:class="[i == currentPage ? 'active' : '']"
            v-on:click="change_page(i)">
          {{i}}
      </div>
  </div>
</div>
</template>
<script src="./Audit.ts" lang="ts"></script>