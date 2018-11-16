import Vue from "vue";
import axios from "axios";

let url = "/audit";

export default Vue.extend({
    data() {
        return {
            audits: [],
            currentPage: 1,
            elementsPerPage: 10,
            username: ""
        }
    },
    methods: {
        get_rows() {
            let start = (this.currentPage-1) * this.elementsPerPage;
            let end = start + this.elementsPerPage;
            return this.audits.slice(start, end);
        },
        num_pages() {
            return Math.ceil(this.audits.length / this.elementsPerPage);
        },
        change_page(page: number) {
            this.currentPage = page;
        },
        get_all_by_username(username: string) {
            let url = "/audit/" + username;
            axios.get(url)
            .then((response: any) => {
                this.audits = response.data;
            });
        }
    },
    computed: {
        columns(): Array<string> {
            if (this.audits.length == 0) {
                return [];
            }
            return Object.keys(this.audits[0]);
        }
    },
    mounted() {
        axios.get(url)
        .then((response: any) => {
            this.audits = response.data;
        });
    }
});