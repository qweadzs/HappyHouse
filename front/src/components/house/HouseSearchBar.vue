<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col cols="2">
      <b-form-select
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col cols="2">
      <b-form-select
        v-model="gugunCode"
        :options="guguns"
        @change="searchApt"
      ></b-form-select>
    </b-col>
    <b-col cols="7">
      <b-form-input
        @keyup.enter="search"
        v-model="keyword"
        :options="guguns"
      ></b-form-input>
    </b-col>
    <div class="cute">
      <v-btn @click="search"><v-icon>mdi-magnify</v-icon></v-btn>
    </div>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }  
*/
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      keyword: "",
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getHouseList",
      "getHouseListSearch",
    ]),
    ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      // console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt() {
      if (this.gugunCode) this.getHouseList(this.gugunCode);
    },
    search() {
      if (!this.gugunCode) {
        alert("구/군을 입력하세요.");
      } else {
        this.getHouseListSearch({
          gugunCode: this.gugunCode,
          keyword: this.keyword,
        });
      }
    },
  },
};
</script>

<style scoped>
.cute {
  /* 원래 있는 위치에서 상대적으로 이동할 위치 */
  position: relative;
  top: 12px;
}
</style>
