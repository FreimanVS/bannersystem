import Vue from "vue";
import AppHelloComponent from "./components/Banners.vue";
import AuditComponent from "./components/Audit.vue";
import HeaderComponent from "./components/Header.vue";

let app = new Vue({
    el: '#app',
    template: '<AppHelloComponent />',
    components: {
        AppHelloComponent
    }
});

let audit = new Vue({
    el: '#audit',
    template: '<AuditComponent />',
    components: {
        AuditComponent
    }
});

let header = new Vue({
    el: '#header',
    template: '<HeaderComponent />',
    components: {
        HeaderComponent
    }
});
