<template>
<div>
  <div id="app">
        <h4>АДМИНСКАЯ</h4>
        <div v-if="mainTable" id="DELETE2">
            <h2>БАННЕРЫ</h2>
            <table>
                <thead>
                <tr>
                    <th v-for="col in columns" v-on:click="sort_table(col)">{{col}}
                        <div class="arrow" v-if="col == sortColumn" v-bind:class="ascending ? 'arrow_up' : 'arrow_down'"></div>
                    </th>
                    <th>actions</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="banner in get_rows()">
                    <td v-for="col in columns">{{banner[col]}}</td>
                    <td>
                        <button @click="edit(banner)"><i>edit</i></button>
                        <button @click="remove(banner)"><i>delete</i></button>
                    </td>
                </tr>


                <tr>
                    <td>
                        NEW:
                    </td>
                    <td>
                        <div>
                            <input placeholder="источник образа" v-model="input.imgSrc" id="imgSrc" type="text">
                        </div>
                    </td>
                    <td>
                        <div>
                            <input placeholder="ширина" v-model="input.width" id="width" type="text">
                        </div>
                    </td>
                    <td>
                        <div>
                            <input placeholder="высота" v-model="input.height" id="height" type="text">
                        </div>
                    </td>
                    <td>
                        <div>
                            <input placeholder="targetUrl" v-model="input.targetUrl" id="targetUrl" type="text">
                        </div>
                    </td>
                    <td>
                        <div>
                            <input placeholder="id языка" v-model="input.langId" id="langId" type="text">
                        </div>
                    </td>
                    <td><button @click="add"><i>add</i></button></td>
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

        <div v-else>
            <div v-for="(banners, key) in groupedBanners" id="groupedTable">
                <table>
                    <thead>
                        <tr>
                            <th v-for="col in columns_grouped(key)" v-on:click="sort_table_grouped(key, col)">{{col}}
                                <div class="arrow" v-if="col == sortColumn" v-bind:class="ascending ? 'arrow_up' : 'arrow_down'"></div>
                            </th>
                            <th>actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="banner in get_rowsGrouped(key)">
                            <td v-for="col in columns_grouped(key)">{{banner[col]}}</td>
                            <td>
                                <button @click="edit(banner)"><i>edit</i></button>
                                <button @click="remove(banner)"><i>delete</i></button>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                NEW:
                            </td>
                            <td>
                                <div>
                                    <input placeholder="источник образа" v-model="input.imgSrc" id="imgSrc" type="text">
                                </div>
                            </td>
                            <td>
                                <div>
                                    <input placeholder="ширина" v-model="input.width" id="width" type="text">
                                </div>
                            </td>
                            <td>
                                <div>
                                    <input placeholder="высота" v-model="input.height" id="height" type="text">
                                </div>
                            </td>
                            <td>
                                <div>
                                    <input placeholder="targetUrl" v-model="input.targetUrl" id="targetUrl" type="text">
                                </div>
                            </td>
                            <td>
                                <div>
                                    <input placeholder="id языка" v-model="input.langId" id="langId" type="text">
                                </div>
                            </td>
                            <td><button @click="add"><i>add</i></button></td>
                        </tr>
                    </tbody>
                </table>
                <div class="pagination">
                    <div class="number"
                         v-for="i in num_pagesGrouped(key)"
                         v-bind:class="[i == currentPageGrouped[key] ? 'active' : '']"
                         v-on:click="change_pageGrouped(i, key)">
                        {{i}}
                    </div>
                </div>
            </div>
        </div>

        <button v-if="mainTable" v-on:click="group_by_language">группировать по имени</button>
        <button v-else v-on:click="group_by_language">без группировки</button>

        <div>
            <div>
                <h4>Изменить</h4>
                <p>Обновить информацию</p>
                <div>
                    <form>
                        <div>
                            <div>
                                <input placeholder="John" id="id" type="text" v-model="editInput.id" hidden>
                            </div>
                            <div>
                                <input placeholder="Doe" id="imgSrc" type="text" v-model="editInput.imgSrc">
                                <label for="imgSrc">источник образа</label>
                            </div>
                        </div>
                        <div>
                            <div>
                                <input placeholder="26" id="width" type="text" v-model="editInput.width">
                                <label for="width">ширина</label>
                            </div>
                            <div>
                                <input placeholder="Teacher" id="height" type="text" v-model="editInput.height">
                                <label for="height">высота</label>
                            </div>
                        </div>
                        <div>
                            <div>
                                <input placeholder="targetUrl" id="targetUrl" type="text" v-model="editInput.targetUrl">
                                <label for="targetUrl">targeturl</label>
                            </div>
                            <div>
                                <input placeholder="id языка" id="langId" type="text" v-model="editInput.langId">
                                <label for="langId">id языка</label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div
                <button @click="update"><i>OK</i></button>
            </div>
        </div>
    </div>
</div>
</template>
<script src="./Banners.ts" lang="ts"></script>