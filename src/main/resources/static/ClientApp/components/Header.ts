import Vue from "vue";
import axios from "axios";

export default Vue.extend({
    data() {
        return {
            name: "",
            contextpath: "",
            action: "/logout"
        }
    },
    mounted() {
      let url = "/index";

      axios({
        url: url,
        headers: {
            'Content-Type': 'application/json'
        },
        method: "GET",
      })
      .then((response) => {
        let contextpath: string = response.headers.contextpath;
        let name: string = response.headers.name;
        this.name = name;
        this.contextpath = contextpath;
        this.action = this.contextpath + this.action;
      })
      .catch((error) => {
        console.log(error);
      });
    }
});
