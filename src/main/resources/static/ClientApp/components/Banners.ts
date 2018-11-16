import Vue from "vue";
import axios from "axios";

let url = "/api/v1/banners";

let groupBy = function(arr: any, key: string) {
    return arr.reduce(function(rv: any, x: any) {
    (rv[x[key]] = rv[x[key]] || []).push(x);
    return rv;
    }, {});
};
let groupByLanguage = function(arr: any) {
    return groupBy(arr, 'langId');
};

class Banner {
    id: number = 0;
    imgSrc: string = '';
    width: number = 0;
    height: number = 0;
    targetUrl: string = '';
    langId: number = 0;

    constructor(o?: object) {
        if (o) {
            this.id = (<Banner>o).id;
            this.imgSrc = (<Banner>o).imgSrc;
            this.width = (<Banner>o).width;
            this.height = (<Banner>o).height;
            this.targetUrl = (<Banner>o).targetUrl;
            this.langId = (<Banner>o).langId;
        }
    }

    get(col: string): (string | number) {
        if (col == "id") {
            return this.id;
        } else if (col == "imgSrc") {
            return this.imgSrc;
        } else if (col == "width") {
            return this.width;
        } else if (col == "height") {
            return this.height;
        } else if (col == "targetUrl") {
            return this.targetUrl;
        } else if (col == "langId") {
            return this.langId;
        } else {
            return "";
        }
    }
}

export default Vue.extend({
    data() {
        return {
            mainTable: true,
            currentPage: 1,
            elementsPerPage: 10,
            currentPageGrouped: new Map<string, number>(),
            ascending: false,
            sortColumn: '',
            banners: [],
            groupedBanners: {},
            input: {
                  id: "",
                  imgSrc: "",
                  width: "",
                  height: "",
                  targetUrl: "",
                  langId: ""
            },
            editInput: {
                  id: 0,
                  imgSrc: "",
                  width: 0,
                  height: 0,
                  targetUrl: "",
                  langId: 0
            }
        }
    },
    methods: {
        sort_table(col: string) {
            if (this.sortColumn === col) {
              this.ascending = !this.ascending;
            } else {
              this.ascending = true;
              this.sortColumn = col;
            }

            var ascending = this.ascending;

            let abc: Banner = new Banner();

            this.banners.sort(function(c: object, d: object): number {
                let a: Banner = new Banner(c);
                let b: Banner = new Banner(d);
                if (a.get(col) > b.get(col)) {
                    return ascending ? 1 : -1
                } else if (a.get(col) < b.get(col)) {
                    return ascending ? -1 : 1
                }
                    return 0;
                })
          },

          sort_table_grouped(key: number, col: string) {
              if (this.sortColumn === col) {
                  this.ascending = !this.ascending;
              } else {
                  this.ascending = true;
                  this.sortColumn = col;
              }

              var ascending = this.ascending;

              
            let groupedBanners: any = this.groupedBanners;
            let banners: Banner[] = groupedBanners[key];

            banners.sort(function(c: object, d: object): number {
                let a: Banner = new Banner(c);
                let b: Banner = new Banner(d);
                  if (a.get(col) > b.get(col)) {
                     return ascending ? 1 : -1
                  } else if (a.get(col) < b.get(col)) {
                     return ascending ? -1 : 1
                  }
                  return 0;
              })

          },

          add() {
              var json = JSON.stringify(this.input);

              axios({
                url: url,
                headers: {
                    'Content-Type': 'application/json'
                },
                method: "POST",
                data: json,
              })
              .then((response) => {
                window.location.reload();
              })
              .catch((error) => {
                console.log(error);
              });
          },
          remove(banner: Banner) {
              var id = banner.id;

              axios({
                url: url + '/' + id,
                method: "DELETE",
              })
              .then((response) => {
                window.location.reload();
              })
              .catch((error) => {
                console.log(error);
              });
          },
          edit(banner: Banner) {
              this.editInput = banner;
          },
          update() {
              var id = this.editInput.id;
              var json = JSON.stringify(this.editInput);

              axios({
                url:  url + '/' + id,
                headers: {
                    'Content-Type': 'application/json'
                },
                method: "PUT",
                data: json,
              })
              .then((response) => {
                window.location.reload();
              })
              .catch((error) => {
                console.log(error);
              });
              
          },
          group_by_language() {
            let result = groupByLanguage(this.banners);
            let keys: Array<string> = Object.keys(result);
            let mapCounter: Map<string, number> = new Map<string, number>();
            keys.forEach(function(key) {
                mapCounter.set(key, 1);
            });

            this.currentPageGrouped = mapCounter;
            this.groupedBanners = result;
            this.mainTable = !this.mainTable;

          },
          num_pages() {
              return Math.ceil(this.banners.length / this.elementsPerPage);
          },
          num_pagesGrouped(key: string) {
            let groupedBanners: any = this.groupedBanners;
            let banners: Banner[] = groupedBanners[key];
              return Math.ceil(banners.length / this.elementsPerPage);
          },
          get_rows() {
              var start = (this.currentPage-1) * this.elementsPerPage;
              var end = start + this.elementsPerPage;
              return this.banners.slice(start, end);
          },
        get_rowsGrouped(key: string) {
            let currentPage: number = this.currentPageGrouped.get(key) as number;
            var start = (currentPage-1) * this.elementsPerPage;
            var end = start + this.elementsPerPage;
            let groupedBanners: any = this.groupedBanners;
            let banners: Banner[] = groupedBanners[key];
            return banners.slice(start, end);
        },
          change_page(page: number) {
              this.currentPage = page;
          },
          change_pageGrouped(page: number, key: string) {
              this.currentPageGrouped.set(key, page);
              this.currentPage = page;
          }
    },
    computed: {
        columns(): Array<string> {
            if (this.banners.length == 0) {
            return [];
            }
            return Object.keys(this.banners[0]);
        },
        columns_grouped: function() {
            let groupedBanners: any = this.groupedBanners;

            return function(key: number) {
                let banners: Banner[] = groupedBanners[key];

                if (banners.length == 0) {
                    return [];
                }
                return Object.keys(banners[0]);
            }
        }
    },
    mounted() {
        axios.get(url)
        .then((response: any) => {
            this.banners = response.data;
        });
    }
});
